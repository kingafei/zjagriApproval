/**
 * 环节弹窗相关js
 */
layui.define(['jquery', 'form', 'table', 'febs', 'laydate', 'layer', 'upload'], function(exports){
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        febs = layui.febs,
        laydate = layui.laydate,
        upload = layui.upload,
        attrIns,
        opinionIns,
        flowLogIns,
        historyIns;
    form.render();
    form.render('select');
    //openSuccess($('#projid').val(), $('#tongid').val());
    /* 打开弹窗成功加载事件*/
    Node = function () {}
    Node.prototype.openSuccess = function (projid, tongid) {
        // 加载基本信息
        $.ajax({
            type: 'get',
            url: ctx + 'apasinfo/getById?projid=' + projid,
            dataType: 'json',
            success: function (result) {
                var apasin = result.data;
                if (apasin.handlestart == '8') {
                    // 展示当前环节步骤
                    showNode(tongid, projid);
                }  else {
                    $(".remove").remove();
                }
                // 基本信息
                form.val("apasinfo", {
                    "projid": apasin.projid,
                    "createTime": apasin.createTime,
                    "syncStatus": apasin.syncStatus,
                    "dataversion": apasin.dataversion,
                    "servicecode": apasin.servicecode,
                    "serviceDeptid": apasin.serviceDeptid,
                    "xkLydw": apasin.xkLydw,
                    "xkLydwdm": apasin.xkLydwdm,
                    "tongid": apasin.tongid,
                    "busType": apasin.busType,
                    "deptname": apasin.deptname,
                    "applyPropertiy": apasin.applyPropertiy,
                    "applyfrom": apasin.applyfrom,
                    "servicename": apasin.servicename,
                    "projectname": apasin.projectname,
                    "applyname": apasin.applyname,
                    "approveType": apasin.approveType,
                    "applyCardtype": apasin.applyCardtype,
                    "applyCardnumber": apasin.applyCardnumber,
                    "legalman": apasin.legalman,
                    "xkFrSfzh": apasin.xkFrSfzh,
                    "contactman": apasin.contactman,
                    "telphone": apasin.telphone,
                    "dtelphone": apasin.dtelphone,
                    "contactmanCardnumber": apasin.contactmanCardnumber,
                    "contactmanCardtype": apasin.contactmanCardtype,
                    "postcode": apasin.postcode,
                    "address": apasin.address,
                    "extend2": apasin.extend2,
                    "projnum": apasin.projnum,
                    "applyType": apasin.applyType,
                    "isTouzip": apasin.isTouzip,
                    "receivetime": apasin.receivetime,
                    "promiseDay": apasin.promiseDay + getDayType(apasin.anticipateType),
                });
                // 材料列表
                initAttr(apasin.tongid, apasin.projid);
                // 申报历史记录
                initHistory(apasin.applyCardnumber);
            }
        });
        // 加载项目办理信息
        $.ajax({
            type: 'post',
            url: ctx + 'accept/getByProjid?projid=' + projid,
            dataType: 'json',
            success: function (result) {
                // 基本信息
                var accept = result.data;
                form.val("apasinfo", {
                    "handerDeptname": accept.handerDeptname,
                    "acceptMan": accept.acceptMan,
                    "acceptTime": accept.acceptTime,
                    "promisevalue": accept.promisevalue,
                    "promisetype": getDayType(accept.promisetype),
                    "promiseEtime": accept.promiseEtime,
                });
            }
        });

    }

    function getDayType(type) {
        switch (type) {
            case '1':
                return '工作日';
                break;
            case '2':
                return '月';
                break;
            case '3':
                return '年';
                break;
            case '4':
                return '天';
                break;
            default:
                return '';
                break;
        }
    }


    /*加载材料列表*/
    function initAttr(tongId, projid) {
        attrIns = febs.table.init({
            elem: '#configureAttr',
            id: "configureAttr",
            url: ctx + 'attr/getByTongId?tongId=' + tongId + '&projid=' + projid,
            cellMinWidth: 80,
            page: false,
            cols: [[
                {field: 'unid', title: '<strong>主键</strong>', hide: true},
                {type: 'numbers', title: '<strong>序号</strong>', width: '7%', align: 'center'},
                {field: 'attrname', title: '<strong>材料名称</strong>', width: '26%', align: 'center'},
                {field: 'amount', title: '<strong>份数</strong>', width: '7%', align: 'center'},
                {field: 'memo', title: '<strong>备注</strong>', width: '15%', align: 'center'},
                {
                    field: 'taketype', title: '<strong>收取方式</strong>', align: 'center', width: '10%', templet: function (d) {
                        switch (d.taketype) {
                            case "1":
                                return "附件上传";
                                break;
                            case "2":
                                return "纸质收取";
                                break;
                            case "3":
                                return "电子证照库";
                                break;
                            default:
                                return "其他";
                                break;
                        }
                    }
                },
                {
                    field: 'istake', title: '<strong>状态</strong>', width: '10%', align: 'center', templet: function (d) {
                        switch (d.sitake) {
                            case "1":
                                return "已收取";
                                break;
                            case "2":
                                return "未收取";
                                break;
                            default:
                                return "其他";
                                break;
                        }
                    }
                },
                {
                    field: 'attrname', title: '<strong>文件</strong>', width: '25.5%', templet: function (d) {
                        var list = d.list,
                            ids = '';
                        var html = '';
                        for (var i in list) {
                            html += '<p>';
                            html += '   <a href="' + list[i].filepath + '">' + list[i].name + '</a>';
                            //html += '   <button onclick="deleteFileByUNID(\'' + list[i].unid + '\',\'' + d.tongid + '\',\'' + d.projid + '\')" class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>';
                            html += '</p>';
                            if (i == 0) {
                                ids += list[i].id;
                            } else {
                                ids += ',' + list[i].id
                            }
                        }
                        html += '<input type="hidden" lay-verify="title" value="' + ids + '" autocomplete="off" class="layui-input">';
                        return html;
                    }
                }
            ]]
        });
    }

    // 加载意见列表（环节表）
    function initOpinion(nodes) {
        opinionIns = febs.table.init({
            elem: '#opinionList',
            id: "opinionList",
            data: nodes,
            cellMinWidth: 80,
            page: false,
            limit: 100,
            cols: [[
                {field: 'unid', title: '<strong>主键</strong>', hide: true},
                {type: 'numbers', title: '<strong>序号</strong>', width: '5%', align: 'center'},
                {field: 'nodeName', title: '<strong>环节</strong>', width: '10%', align: 'center'},
                {
                    field: 'nodeName',
                    title: '<strong>意见类型</strong>',
                    width: '15%',
                    align: 'center',
                    templet: function (d) {
                        return d.nodeName + '意见';
                    }
                },
                {field: 'opinion', title: '<strong>意见内容</strong>', width: '15%', align: 'center'},
                {field: 'name', title: '<strong>意见发表者</strong>', width: '10%', align: 'center'},
                {field: 'startTime', title: '<strong>环节处理时间</strong>', width: '18%', align: 'center'},
                {
                    field: 'extend',
                    title: '<strong>环节附件</strong>',
                    width: '27.5%',
                    align: 'center',
                    templet: function (d) {
                        var list = d.fileList;
                        var html = '';
                        for (var i in list) {
                            html += '<p style="color: #384dff">' + list[i].originalName + '</p>';
                        }
                        return html;;
                    }
                }
            ]]
        });
    }

    /*加载申报历史记录数据*/
    function initHistory(cardId) {
        historyIns = febs.table.init({
            elem: '#history',
            id: "history",
            url: ctx + 'apasinfo/getByCardId?cardId=' + cardId,
            page: false,
            limit: 100,
            cols: [[
                {type: 'numbers', title: '<strong>序号</strong>', width: '6%', align: 'center'},
                {field: 'projectname', title: '<strong>项目名称</strong>', width: '25%', align: 'center'},
                {field: 'servicename', title: '<strong>审批事项</strong>', width: '24%', align: 'center'},
                {field: 'projid', title: '<strong>申报号</strong>', width: '20%', align: 'center'},
                {field: 'receivetime', title: '<strong>申报时间</strong>', width: '16%', align: 'center'},
                {
                    title: '操作', width: '9.25%', align: 'center', templet: function (d) {
                        return "<a onclick=\"look('" + d.projid + "')\" class=\"layui-btn layui-btn-xs\"> 查看</a>";
                    }
                }
            ]]
        });
    }
    // 加载流转日志（环节表）
    function initFlowLog(nodes) {
        flowLogIns = febs.table.init({
            elem: '#flowLog',
            id: "flowLog",
            data: nodes,
            cellMinWidth: 80,
            page: false,
            limit: 100,
            cols: [[
                {field: 'unid', title: '<strong>主键</strong>', hide: true},
                {type: 'numbers', title: '<strong>序号</strong>', width: '10%', align: 'center'},
                {field: 'name', title: '<strong>办理人</strong>', width: '17%', align: 'center'},
                {field: 'nodeName', title: '<strong>操作</strong>', width: '17%', align: 'center'},
                {field: 'action', title: '<strong>业务动作</strong>', width: '17%', align: 'center'},
                {field: 'extend', title: '<strong>接收人</strong>', width: '17%', align: 'center'},
                {field: 'startTime', title: '<strong>时间</strong>', width: '22.3%', align: 'center'}
            ]]
        });
    }

    // 点击材料删除按钮时删除材料
    // deleteFileByUNID = function (unid, tongid, projid) {
    //     if (confirm('确定要删除吗？')) {
    //         $.ajax({
    //             type: 'get',
    //             url: ctx + 'formfile/deleteByUUID?unid=' + unid,
    //             dataType: 'json',
    //             success: function (result) {
    //                 initAttr(tongid, projid);
    //             }
    //         })
    //     }
    // }

    function showNode(tongid,projid) {
        $.ajax({
            type: 'post',
            url: ctx + 'node/getByProjid?tongid=' + tongid + '&projid=' + projid,
            dataType: 'json',
            success: function (result) {
                var nodes = result.data.nodes;
                var flows = result.data.flows;
                // 寻找当前环节坐标
                var nowIndex = 0;
                var endnode = nodes[nodes.length - 1];
                $.each(flows, function (index, item) {
                    if (item.note == endnode.nodeName) {
                        if (endnode.action == '退回') {
                            nowIndex = index - 1;
                        } else {
                            nowIndex = index + 1;
                        }
                    }
                })
                //============START======加载环节相关信息==========================
                initOpinion(nodes);
                initFlowLog(nodes);
                //环节坐标
                var html = '<input type="hidden" name="nodeIndex" value="' + nowIndex + '" lay-verify="title" autocomplete="off"  class="layui-input">';
                // 当前环节开始时间（上个环节结束时间）
                html += '<input type="hidden" name="nodeStartTime" value="' + nodes[nodes.length - 1].endTime + '" lay-verify="title" autocomplete="off"  class="layui-input">';
                $.each(flows, function (index, item) {
                    if (index < nowIndex) {
                        html += '<button class="layui-btn" style="background-color: #1e51c7;">' + item.note + '</button>';
                    } else if (index == nowIndex) {
                        html += '<button class="layui-btn" style="background-color: #F5081B;">' + item.note + '</button>';
                    } else {
                        html += '<button class="layui-btn" style="background-color: #909399;">' + item.note + '</button>';
                    }
                })
                $('#flows').html(html);
                //============END=====加载环节相关信息==========================
            }
        })
    }


    look = function (projid) {
        layer.msg("查看历史记录详情" + projid);
    }

    var node = new Node();
    exports("nodeopen", node);
})