/**
 * 环节弹窗相关js
 */
layui.define(['jquery', 'form', 'table', 'febs', 'laydate', 'layer'], function(exports){
    var $ = layui.jquery,
        table = layui.table,
        febs = layui.febs,
        $view = $('#febs-apasinfo'),
        $searchForm = $view.find('form'),
        $query = $view.find('#query'),
        sortObject = {field: 'createTime', type: null},
        createTimeFrom,
        createTimeTo,
        tableIns;

    Waiting = function(){};
    var waiting = new Waiting();
    /*加载table表格分页数据*/
    Waiting.prototype.initTable = function() {
        tableIns = febs.table.init({
            elem: '#configureApasinfo',
            id: 'configureApasinfo',
            url: ctx + 'apasinfo/tables/info?handlestart=5,6,7',
            cols: [[
                // {type: 'checkbox'},
                {type: 'numbers', title: '<strong>序号</strong>', width: '5%'},
                {field: 'projectname', title: '<strong>项目名称</strong>'},
                {field: 'servicename', title: '<strong>审批事项</strong>'},
                {field: 'projid', title: '<strong>申报号</strong>'},
                {field: 'applyname', title: '<strong>申请人</strong>'},
                {field: 'acceptTime', title: '<strong>受理时间</strong>'},
                {
                    field: 'handlestart', title: '<strong>当前状态</strong>', templet: function (d) {
                        switch (d.handlestart) {
                            case '1':
                                return '草稿';
                                break;
                            case '2':
                                return '收件';
                                break;
                            case '3':
                                return '预受理';
                                break;
                            case '4':
                                return '预受理退回';
                                break;
                            case '5':
                                return '受理';
                                break;
                            case '6':
                                return '补齐补正';
                                break;
                            case '7':
                                return '不予受理';
                                break;
                            case '8':
                                return '在办';
                                break;
                            case '9':
                                return '挂起';
                                break;
                            case '10':
                                return '办结';
                                break;
                            case '11':
                                return '转报办结';
                                break;
                            case '12':
                                return '作废办结';
                                break;
                            case '13':
                                return '退件';
                                break;
                            default:
                                return '';
                                break;
                        }
                    }
                },
                {field: 'applyCardnumber', title: '<strong>申请人证件号码</strong>', hide: true},
                {
                    title: '<strong>操作</strong>', width: '10%', toolbar: '#applyopen', align: 'center'
                }
            ]]
        });
    }



    // 办件列表条件查询
    table.on('tool(configureApasinfo)', function (obj) {
        var data = obj.data,
            layEvent = obj.event;
        if (layEvent === 'applyopen') {//待办业务
            febs.modal.open('办件处理', 'qlsx/nodeopen/' + data.projid + '/' + data.tongid, {
                area: $(window).width() <= 750 ? '90%' : '1000px',
                btn: [ '受理','不予受理','关闭'],
                yes: function (index, layero) {
                    if (confirm('确定要开始办理此件吗？')) {
                        acceppt();
                        layer.close(index)
                    }
                }, btn2: function (index, layero) {
                    if (confirm('确定要此操作吗？')) {
                        notHandele('7');
                    } else {
                        return false;
                    }
                }, btn3: function (index, layero) {
                    if (!confirm('确定要关闭吗？')) {
                        return false;
                    }
                }, cancel: function (index, layero) {
                    if (confirm('确定要关闭吗？当前操作将不会被保存！！')) {
                        layer.close(index)
                    }
                    return false;
                }
            })
        }
    })


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


    // 受理点击事件
    function acceppt() {
        // 阶段和状态
        var handlestart = '8';
        var phaseCode = '03';
        // 表单数据
        var apasinfo = getFormData();
        //修改数据状态
        if (handlestart != null) {
            apasinfo['handlestart'] = handlestart;
        }
        // 表格数据
        var tableData = getTableData();
        // 请求提交受理信息，和阶段信息
        $.ajax({
            type: 'POST',//方法类型
            url: ctx + 'node/node',
            data: {
                'apasinfo': JSON.stringify(apasinfo), // 申报信息
                'tableData': tableData, // 材料信息
                'handlestart': handlestart, // 办件状态
                'phaseCode': phaseCode, // 办件阶段
                'nodeIndex': '0', // 环节坐标
                'opinion': apasinfo.opinion, // 审批意见
                'nodeFile': apasinfo.nodeFile // 附件id
            },
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("办理成功");
                    waiting.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    // 不予受理点击事件
    function notHandele(handlestart) {
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
                    layer.msg("操作成功");
                    waiting.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    exports('waiting', waiting);

})