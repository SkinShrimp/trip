<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl">
    <link rel="stylesheet" href="/js/plugins/treeview/bootstrap-treeview.min.css"/>
<#--treeview插件-->
    <script src="/js/plugins/treeview/bootstrap-treeview.min.js"></script>
<#--表单提交插件-->
    <script src="/js/jquery.form.js"></script>
</head>

<script type="text/javascript">
    $(function () {
        $.get("/region/listByParentId.do", {type: "tree"}, function (data) {
            var defaultData = [{
                text: '全部地区',
                nodes: data,
            }];
            $('#treeview').treeview({
                showTags: true,
                data: defaultData,
                lazyLoad: function (node) {//开起赖加载
                    $.get("/region/listByParentId.do", {parentId: node.id, type: "tree"},
                            function (data) {
                                $('#treeview').treeview("addNode", [data, node]);
                            })
                },
                onNodeSelected: function (event, data) {
                    //添加按钮
                    $("#addRegionBtn").attr("data-json", '{"parentId":"' + data.id + '", "parentName":"' + data.text + '"}');
                    $.get("/region/listByParentId.do", {parentId: data.id}, function (data) {
                        // var temp = "";
                        // //添加到tbody
                        // $.each(data, function (index, ele) {
                        //     temp += "<tr><td>" + index + 1 + "</td><td>" + ele.name + "</td></tr>";
                        //     $("tbody").html(temp);
                        // });

                        $("#regionTB tbody").empty();
                        //使用clone的方式将模板加到左边
                        $.each(data, function (index, ele) {
                            var tr = $("#template tr").clone(true);
                            //设置内容
                            $(tr).find("td:nth-child(1)").html(index + 1);
                            $(tr).find("td:nth-child(2)").html(ele.name);
                            $(tr).find("a").attr("data-json", ele.json);

                            //修改推荐的状态
                            // var parse = JSON.parse(ele.json);
                            // if (parse.state == 0) {
                            //     $(tr).find("td:nth-child(4) > a").html("设为推荐");
                            // } else {
                            //     $(tr).find("td:nth-child(4) > a").html("取消推荐");
                            // }

                            //修改推荐的状态(升级)
                            if(ele.state == 1){
                                $(tr).find("a:last").html("取消推荐");
                            }

                            $("#regionTB tbody").append(tr);
                        });

                        $(".btn-edit").click(function () {
                            var json = $(this).data("json");
                            $("[name='id']").val(json.id);
                            $("[name='parent.name']").val(json.parentName);
                            $("[name='name']").val(json.name);

                            $("#regionModal").modal("show");
                        });
                    })
                }
            });
        });

        //修改增加公用地区
        $("#saveBtn").click(function () {
            $.messager.confirm("温馨提示", "亲!您确定要更新当前数据么?", function () {
                $("#editForm").ajaxSubmit(function (data) {
                    if (data.success) {
                        window.location.reload();
                    }
                });
            });
        });

        //取消/推荐状态
        $(".btn-recomment").click(function () {
            var json = $(this).data("json");
            var state;
            if (json.state == 1) {
                state = 0;
            } else {
                state = 1;
            }
            var temp = "";
            temp += "?id=" + json.id + "&state=" + state;
            $.post("/region/saveOrUpdate.do" + temp, function (data) {
                if (data.success) {
                    $.messager.confirm("温馨提示", "设置成功！", function () {
                        window.location.reload();
                    });
                }
            })
        });

        //添加地区
        $("#addRegionBtn").click(function () {
            document.getElementById("editForm").reset();

            var str = $(this).attr("data-json");
            if (str == "") {
                $.messager.alert("温馨提示，未选择上一级地区，本次添加为第一级！")
            } else {
                var json = JSON.parse(str);
                $("[name='parent.name']").val(json.parentName);
                if (json.parentName != "全部地区") {
                    $("[name='parent.id']").val(json.parentId);
                }

            }

            $("#regionModal").modal("show");

        });

        //删除
        $(".btn-delete").click(function () {
            var json = $(this).data("json");
            $.messager.confirm("温馨提示", "亲!您确定要删除当前数据么?", function () {
                $.get("/region/delete.do?id=" + json.id, function (data) {
                    if (data.success) {
                        window.location.reload();
                    }
                });
            });

        });
        //新增地区
        // var defaultData = [
        //     {
        //         text: 'Parent 1',
        //         showTags: true,
        //         tags: ['4'],
        //         nodes: [
        //             {
        //                 text: 'Child 1',
        //                 tags: ['推荐'],
        //                 nodes: [
        //                     {
        //                         text: 'Grandchild 1',
        //                         tags: ['0']
        //                     },
        //                     {
        //                         text: 'Grandchild 2',
        //                         tags: ['0']
        //                     }
        //                 ]
        //             },
        //             {
        //                 text: 'Child 2',
        //                 tags: ['0']
        //             }
        //         ]
        //     }
        // ];


    });

</script>
<body>

<#--隐藏模板-->
<table id="template" style="display: none">
    <tr>
        <td></td>
        <td></td>
        <td>
            <a class="btn btn-info btn-xs btn-edit" href="javascript:;">
                <span class="glyphicon glyphicon-pencil"></span>编辑</a>
            <a href="javascript:;" class="btn btn-danger btn-xs btn-delete">
                <span class="glyphicon glyphicon-trash"></span>删除
            </a>
        </td>
        <td>
            <a href="javascript:;" class="btn btn-pencil btn-xs btn-recomment">
                <span class="glyphicon"></span>设为推荐
            </a>
        </td>
    </tr>
</table>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "permission"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">地区管理</h1>
                </div>
                <div class="row">
                    <div class="form-group" style="margin-left: 20px">
                        <a href="javascript:void(-1);" class="btn btn-success" id="addRegionBtn" data-json="">添加地区</a>
                    </div>
                </div>
                <br/>
            </div>

            <div class="row">
                <div class="col-sm-4">
                    <div id="treeview"></div>
                </div>
                <div class="col-sm-8">
                    <table class="table table-striped table-hover" id="regionTB">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>名称</th>
                            <th>操作</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<#--修改模态框-->
<div id="regionModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" method="post" action="/region/saveOrUpdate.do">
                    <input id="regionId" type="hidden" name="id" value=""/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" placeholder="地区/景区名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">上级地区</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="parent.name" readonly>
                            <input type="hidden" class="form-control" name="parent.id">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>


</body>
</html>