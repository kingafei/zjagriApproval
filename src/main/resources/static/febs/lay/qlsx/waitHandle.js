/**
 * 环节弹窗相关js
 */
layui.define(['jquery', 'form', 'table', 'febs', 'laydate', 'layer', 'upload','dict'], function(exports){
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        febs = layui.febs,
        laydate = layui.laydate,
        upload = layui.upload,
        dict = layui.dict,
        $view = $('#febs-apasinfo'),
        $query = $view.find('#query'),
        $reset = $view.find('#reset'),
        $searchForm = $view.find('form'),
        sortObject = {field: 'createTime', type: null},
        createTimeFrom,
        createTimeTo,
        tableIns;

    var WaitHandle = function () {}
    var waitHandle = new WaitHandle();
    /*加载table表格分页数据*/
    WaitHandle.prototype.initTable = function() {
        tableIns = febs.table.init({
            elem: '#configureApasinfo',
            id: 'configureApasinfo',
            url: ctx + 'apasinfo/tables/info?handlestart=1,2,3,4',
            cols: [[
                // {type: 'checkbox'},
                {type: 'numbers', title: '<strong>序号</strong>', width: '5%'},
                {field: 'servicename', title: '<strong>事项名称</strong>'},
                {field: 'projid', title: '<strong>申报号</strong>'},
                {field: 'applyname', title: '<strong>申请人</strong>'},
                {
                    field: 'applyfrom', title: '<strong>来源</strong>', templet: function (d) {
                        switch (d.applyfrom) {
                            case '0':
                                return '部门业务系统窗口收件';
                                break;
                            case '1':
                                return 'pc端网上申报';
                                break;
                            case '2':
                                return '移动端网上申报';
                                break;
                            case '3':
                                return '上级交办';
                                break;
                            case '4':
                                return '下级报送';
                                break;
                            case '5':
                                return '同级转办';
                                break;
                            case '8':
                                return '线下一窗受理平台申报';
                                break;
                            case '9':
                                return '线上一窗受理平台收件';
                                break;
                            default:
                                return '';
                                break;
                        }
                    }
                },
                {
                    field: 'handlestart', title: '<strong>办件状态</strong>', templet: function (d) {
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
                {field: 'createTime', title: '<strong>收件时间</strong>'},
                {field: 'applyCardnumber', title: '<strong>申请人证件号码</strong>', hide: true},
                {
                    title: '<strong>操作</strong>', width: '10%', toolbar: '#receivejob', align: 'center'
                }
            ]]
        });
    }

    // 点击打开弹窗
    table.on('tool(configureApasinfo)', function (obj) {
        var data = obj.data,
            layEvent = obj.event;

        if (layEvent === 'receivejob') {//收件事件

            febs.modal.open('收件', 'qlsx/proReceive/' + data.projid, {
                area: $(window).width() <= 750 ? '90%' : '900px',
                btn: ['保存', '预受理', '受理', '预受理退回'],
                cancel: function (index, layero) {
                    if (confirm('确定要关吗？当前操作将不会被保存！！')) {
                        layer.close(index)
                    }
                    return false;
                },
                yes: function (index, layero) {
                    if (confirm('确定要保存吗？')) {
                        save(null);
                        layer.close(index)
                    }
                }, btn2: function (index, layero) {
                    if (confirm('确定要预受理吗？')) {
                        save(3);
                    } else {
                        return false;
                    }
                }, btn3: function (index, layero) {
                    if (confirm('确定要受理吗？')) {
                        acceppt();
                    } else {
                        return false;
                    }
                }, btn4: function (index, layero) {
                    if (confirm('确定要预受理退回吗？')) {
                        save(4)
                    } else {
                        return false;
                    }
                }
            });

        }

    });


    table.on('sort(configureApasinfo)', function (obj) {
        sortObject = obj;
        tableIns.reload({
            initSort: obj,
            where: $.extend(getQueryParams(), {
                field: obj.field,
                order: obj.type
            })
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

    $reset.on('click', function () {
        $searchForm[0].reset();
        treeSelect.revokeNode('dept');
        sortObject.type = 'null';
        createTimeTo = null;
        createTimeFrom = null;
        tableIns.reload({where: getQueryParams(), page: {curr: 1}, initSort: sortObject});
    });


    // 阻止点击删除按钮是表单自动提交问题
    form.on('submit(apasinfo)', function (data) {
        return false;
    });


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
        var amount = $('#attrTable [name="amount"]').serializeArray();
        var memo = $('#attrTable [name="memo"]').serializeArray();
        var taketype = $('#attrTable [name="taketype"]').serializeArray();
        var unid = $('#attrTable [name="unid"]').serializeArray();
        var projid = $('#attrTable [name="projid"]').serializeArray();
        var attrname = $('#attrTable [name="attrname"]').serializeArray();
        var attrid = $('#attrTable [name="attrid"]').serializeArray();
        var extend = $('#attrTable [name="extend"]').serializeArray();
        var tableData = new Array();
        $.each(unid, function (index, item) {
            var ob = {};
            ob[item.name] = item.value;
            ob[amount[index].name] = amount[index].value;
            ob[memo[index].name] = memo[index].value;
            ob[taketype[index].name] = taketype[index].value;
            ob[projid[index].name] = projid[index].value;
            ob[attrname[index].name] = attrname[index].value;
            ob[attrid[index].name] = attrid[index].value;
            ob[extend[index].name] = extend[index].value;
            tableData[index] = ob;
        })
        var data = JSON.stringify(tableData);
        console.log(data)
        //console.log(data);
        return data;
    }

    // 点击保存事件
    function save(handlestart) {
        //获取表单数据并提交
        var apasinfo = getFormData();
        //修改数据状态
        if (handlestart != null) {
            apasinfo['handlestart'] = handlestart;
        }
        //获取table表格数据并提交
        var tableData = getTableData();
        $.ajax({
            type: 'POST',//方法类型
            url: ctx + 'apasinfo/updateAll',
            data: {'apasinfo': JSON.stringify(apasinfo), 'tableData': tableData},
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("保存成功");
                    waitHandle.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    // 受理点击事件
    function acceppt() {
        // 阶段和状态
        var handlestart = '5';
        var phaseCode = '02';
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
            url: ctx + 'accept/accept',
            data: {
                'apasinfo': JSON.stringify(apasinfo),
                'tableData': tableData,
                'handlestart': handlestart,
                'phaseCode': phaseCode
            },
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("受理成功");
                    waitHandle.initTable();
                } else {
                    layer.msg(result.message)
                }
            }
        })
    }

    exports('waitHandle',waitHandle);
})