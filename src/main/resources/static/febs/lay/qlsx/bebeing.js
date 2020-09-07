/**
 * 环节弹窗相关js
 */
layui.define(['jquery', 'form', 'table', 'febs', 'laydate', 'layer'], function(exports){
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        febs = layui.febs,
        $view = $('#febs-apasinfo'),
        $searchForm = $view.find('form'),
        $query = $view.find('#query'),
        sortObject = {field: 'createTime', type: null},
        createTimeFrom,
        createTimeTo,
        tableIns;

    Bebeing = function(){};
    var bebeing = new Bebeing();
    /*加载table表格分页数据*/
    Bebeing.prototype.initTable = function() {
        tableIns = febs.table.init({
            elem: '#ApasinfoList',
            id: 'ApasinfoList',
            url: ctx + 'apasinfo/tables/info?handlestart=8',
            cols: [[
                {type: 'numbers', title: '<strong>序号</strong>', width: '5%'},
                {field: 'projid', title: '<strong>申报号</strong>'},
                {field: 'projectname', title: '<strong>项目名称</strong>'},
                {field: 'applyname', title: '<strong>申请人</strong>'},
                {field: 'servicename', title: '<strong>事项名称</strong>'},
                {field: 'applyCardnumber', title: '<strong>身份证号</strong>'},
                {field: 'acceptTime', title: '<strong>受理时间</strong>'},
                {field: 'promiseEtime', title: '<strong>承诺办结时间</strong>'}
            ]]
        });
    }

    // 双击打开弹窗
    table.on('rowDouble(ApasinfoList)', function (obj) {
        var data = obj.data;
        obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        $.ajax({
            type: 'post',
            url: ctx + 'node/getByProjid?tongid=' + data.tongid + '&projid=' + data.projid,
            dataType: 'json',
            success: function (result) {
                //============START=====打开展示当前环节按钮==========================
                var nodes = result.data.nodes;
                var flows = result.data.flows;
                // 寻找当前环节坐标
                var nowIndex = 0;
                var endnode = nodes[nodes.length-1];
                $.each(flows, function (index, item) {
                    if (item.note == endnode.nodeName) {
                        if (endnode.action == '退回') {
                            nowIndex = index - 1;
                        } else {
                            nowIndex = index + 1;
                        }
                    }
                })
                //============END=====打开展示当前环节按钮==========================

                febs.modal.open('办件处理', 'qlsx/nodeopen/' + data.projid + '/' + data.tongid, {
                    area: $(window).width() <= 750 ? '90%' : '900px',
                    btn: [flows[nowIndex].note, '退回', '挂起', '关闭'],
                    yes: function (index, layero) {
                        if (confirm('确定要开始办理此件吗？')) {
                            if (flows.length-1 == nowIndex) {
                                nodeEnd('10');
                            } else {
                                nodeNext();
                            }
                            layer.close(index)
                        }
                    },  btn2: function (index, layero) {
                        if(confirm('确定要退回吗？')){
                            if (nowIndex == 0) {
                                layer.msg('当前环节已是最初环节，不可退回！');
                                return false;
                            }
                            nodeBack();
                        } else {
                            return false;
                        }
                    }, btn3: function (index, layero) {
                        if (confirm('确定要挂起吗？')) {
                            hangUp(9);
                        } else {
                            return false;
                        }
                    }, btn4: function (index, layero) {
                        if (!confirm('确定要关闭吗？')) {
                            return false;
                        }
                    }, cancel: function (index, layero) {
                        if (confirm('确定要关闭吗？当前操作将不会被保存！！')) {
                            layer.close(index)
                        }
                        return false;
                    }
                });
            }
        });
    });


    // 办件列表条件查询
    $query.on('click', function () {
        var params = $.extend(getQueryParams(), {field: sortObject.field, order: sortObject.type});
        tableIns.reload({where: params, page: {curr: 1}});
    });

    function getQueryParams() {
        var params = $searchForm.serializeJson();
        var createTime = params.time;
        if (createTime) {
            createTimeFrom = createTime.split(' - ')[0];
            createTimeTo = createTime.split(' - ')[1];
        }
        params.invalidate_ie_cache = new Date();
        params.createTimeFrom = createTimeFrom;
        params.createTimeTo = createTimeTo;
        return params;
    }



    // 获取表单数据
    function getFormData() {
        var d = {};
        var t = $('#apasinfo [name]').serializeArray();
        $.each(t, function () {
            d[this.name] = this.value;
        });
        return d;
    }

    // 获取材料表格数据
    function getTableData() {
        var tableData = layui.table.cache.configureAttr;
        var data = JSON.stringify(tableData);
        //console.log(data);
        return data;
    }


    // 环节点击
    function nodeNext() {
        // 表单数据
        var apasinfo = getFormData();
        // 请求提交办结信息，和阶段信息
        $.ajax({
            type: 'POST',//方法类型
            url: ctx + 'node/nodeNext',
            data: {
                'apasinfo': JSON.stringify(apasinfo), // 申报信息
                'nodeIndex': apasinfo.nodeIndex, // 环节坐标
                'opinion': apasinfo.opinion, // 审批意见
                'nodeStartTime': apasinfo.nodeStartTime, // 当前环节开始时间（上个环节结束时间)
                'nodeFile': apasinfo.nodeFile // 附件id
            },
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("办理成功");
                    bebeing.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    // 退回
    function nodeBack() {
        // 表单数据
        var apasinfo = getFormData();
        // 请求提交办结信息，和阶段信息
        $.ajax({
            type: 'POST',//方法类型
            url: ctx + 'node/nodeNext',
            data: {
                'apasinfo': JSON.stringify(apasinfo), // 申报信息
                'nodeIndex': apasinfo.nodeIndex, // 环节坐标
                'opinion': apasinfo.opinion, // 审批意见
                'nodeStartTime': apasinfo.nodeStartTime, // 当前环节开始时间（上个环节结束时间)
                'nodeFile': apasinfo.nodeFile, // 附件id
                'action': '退回' // 业务动作
            },
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("退回成功");
                    bebeing.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    // 挂起
    function hangUp(handlestart) {
        // 表单数据
        var apasinfo = getFormData();
        //修改数据状态
        if (handlestart != null) {
            apasinfo['handlestart'] = handlestart;
        }
        // 请求提交不予受理信息
        $.ajax({
            type: 'POST',//方法类型
            url: ctx + 'apasinfo/updateAll',
            data: {'apasinfo': JSON.stringify(apasinfo), 'tableData': getTableData()},
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("挂起成功");
                    bebeing.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    // 办结点击事件
    function nodeEnd(handlestart) {
        // 阶段和状态
        var phaseCode = '04';
        // 表单数据
        var apasinfo = getFormData();
        //修改数据状态
        if (handlestart != null) {
            apasinfo['handlestart'] = handlestart;
        }
        // 请求提交办结信息，和阶段信息
        $.ajax({
            type: 'POST',//方法类型
            url: ctx + 'transact/updteAndSave',
            data: {
                'apasinfo': JSON.stringify(apasinfo), // 申报信息
                'phaseCode': phaseCode, // 办件阶段
                'nodeIndex': apasinfo.nodeIndex, // 环节坐标
                'opinion': apasinfo.opinion, // 审批意见
                'nodeStartTime': apasinfo.nodeStartTime, // 当前环节开始时间（上个环节结束时间)
                'nodeFile': apasinfo.nodeFile // 附件id
            },
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("办结成功");
                    bebeing.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    exports("bebeing", bebeing);

})