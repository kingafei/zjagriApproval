/**
 * 扩展layui组件
 */
layui.define(['element','jquery'], function(exports){
    var element = layui.element,
        $ = layui.$;
    FsForm = function () {}
    FsForm.prototype.renderDictAll = function (formId, b) {
        var element = layui.element,
            $ = layui.$;
        $.ajaxSettings.async = false; //设置为同步，否则layui会先渲染表格，导致数据显示不出来
        //此处拿到的dict即字典code，可以通过此字典code去数据库或者redis中查询相应的字典数据并加载到select中的option中。
        $('.selDict').each(function(){
            var _this = $(this);
            var dict = _this.attr("dict");
            $.get(ctx + 'NytDataDict/selectByPid?pid='+ dict, function(data){//后台获取数据，这里需要根据返回数据，自己调整。我这里返回数据见下面代码
                if(data.data!=null){
                    $.each(data.data,function(index,m){
                        _this.append("<option value='"+m.value+"'>"+m.text+"</option>");
                    });
                }
            });
        })
       $.ajaxSettings.async = true; //ajax恢复为异步
    }
    var fsForm = new FsForm();
    exports("dict", fsForm);
})