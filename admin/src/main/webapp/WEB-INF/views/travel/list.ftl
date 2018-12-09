<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl">

    <style>
        .modal-body img {
            width: 100%;
        }
    </style>
</head>

<script type="text/javascript">
    $(function () {
        //修改文章发布状态
        $(".btn-release").click(function () {
            var id = $(this).data("id");
            var state = $(this).data("state");
            $.messager.confirm("温馨提示", "确定发布?", function () {
                $.post("/travel/changeState.do", {id: id, state: state}, function (data) {
                    successAlert(data);
                });
            });
        });

        //拒绝文章
        $(".btn-trash").click(function () {
            var id = $(this).data("id");
            var state = $(this).data("state");
            $.messager.confirm("温馨提示", "确定拒绝?", function () {
                $.post("/travel/changeState.do", {id: id, state: state}, function (data) {
                    successAlert(data);
                });
            });
        });

        //查看文章
        $(".btn-look").click(function () {
            var id = $(this).data("id");
            $.get("/travel/get.do?id=" + id, function (data) {
                $(".modal-body").html(data.content);
                $("#lookModal").modal("show");
            });
        });
    });
</script>

<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "audit_list"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">游记管理</h1>
                </div>
            </div>

            <form id="searchForm" class="form-inline" method="post" action="/travel/list.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入标题关键字">
                </div>
                <div class="form-group">
                    <select id="auditState" class="form-control" autocomplete="off" name="state">
                        <option value="-2">全部</option>
                        <option value="1">待发布</option>
                        <option value="3">已拒绝</option>
                    </select>
                    <script>
                        $("#auditState").val("${(qo.state)!}")
                    </script>
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                </div>
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>地点</th>
                    <th>作者</th>
                    <th>状态</th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${(entity.title)!}</td>
                        <td><img src="${(entity.coverUrl)!}" style="width: 50px;"/></td>
                        <td>${(entity.place.name)!}</td>
                        <td>${(entity.author.nickName)}</td>
                        <td>${entity.currentState}</td>
                        <td>
                        <td>
                        </td>
                        <td>
                            <a class="btn btn-info btn-xs btn-release" href="javascript:;" data-state="2"
                               data-id="${entity.id}">
                                <span class="glyphicon glyphicon-pencil"></span>发布</a>
                        </td>
                        <td>
                            <a href="javascript:;" class="btn btn-danger btn-xs btn-trash" data-state="3"
                               data-id="${entity.id}">
                                <span class="glyphicon glyphicon-trash"></span>拒绝
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-info btn-xs btn-look" href="javascript:;" data-id="${entity.id}">
                                <span class="glyphicon glyphicon-pencil "></span>查看游记</a>
                        </td>
                    </tr>
                </#list>
            </table>

            <div style="text-align: center;">
                <#include "../common/page.ftl">
            </div>
        </div>
    </div>
</div>

<#--修改模态框-->
<div id="lookModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">查看内容</h4>
            </div>

            <div class="modal-body">
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