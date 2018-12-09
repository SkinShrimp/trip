<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl">
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <style>
        .modal-body img {
            width: 100%;
        }
    </style>

</head>

<script type="text/javascript">
    $(function () {
        //取消发布文章
        $(".btn-trash").click(function () {
            var id = $(this).data("id");
            var state = $(this).data("state");
            $.messager.confirm("温馨提示", "确定取消发布?", function () {
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

        //推荐文章 初始化
        $(".btn-pencil").click(function () {
            var json = $(this).data("json");
            $("[name='travel.id']").val(json.id);
            $("[name=title]").val(json.title);
            console.log(json);
            console.log(json.coverUrl);
            $("#coverUrl").attr("src", json.coverUrl);
            $("#travelCommendModal").modal("show");
        });

        //推荐文章 保存
        $("#saveCommendBtn").click(function () {
            $.messager.confirm("温馨提示", "确定推荐?", function () {
                $("#commendForm").ajaxSubmit(function (data) {
                    if (data.success) {
                        successAlert(data);
                    }
                });
            });
        });
    });
</script>

<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "travel"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">已发布游记管理</h1>
                </div>
            </div>

            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/travel/releaseList.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!}">
                </div>
                <div class="form-group">
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
                    <th>发布时间</th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${(entity.title)!}</td>
                        <td><img src="${(entity.coverUrl)!}" style="width: 50px;"/></td>
                        <td>${(entity.place.name)!}</td>
                        <td>${(entity.author.nickName)}</td>
                        <td>${(entity.releaseTime)!}</td>
                        <td>
                        <td>
                        </td>
                        <td>
                            <a class="btn btn-info btn-xs btn-look" href="javascript:;"
                               data-id="${entity.id}">
                                <span class="glyphicon glyphicon-pencil"></span>查看游记</a>
                        </td>
                        <td>
                            <a href="javascript:;" class="btn btn-danger btn-xs btn-trash" data-state="1"
                               data-id="${entity.id}">
                                <span class="glyphicon glyphicon-trash"></span>取消发布
                            </a>
                        </td>
                        <td>
                            <a class="btn btn-info btn-xs btn-pencil" href="javascript:;" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil "></span>推荐</a>
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


<#--推荐-->
<div id="travelCommendModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置为推荐</h4>
            </div>
            <div class="modal-body">
                <form id="commendForm" class="form-horizontal" method="post" action="/travelCommend/saveOrUpdate.do"
                      enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="travelCommendId" type="hidden" name="id" value=""/>
                    <input id="travelId" type="hidden" name="travel.id" value=""/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" placeholder="标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="subTitle" placeholder="副标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img width="200px" id="coverUrl"/>
                            <input type="hidden" name="coverUrl">
                            <input type="file" class="form-control" name="file">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐时间</label>
                        <div class="col-sm-6">
                            <input type="text" name="schedule" class="form-control" onclick="WdatePicker()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" autocomplete="off" name="type">
                                <option value="1">每月推荐</option>
                                <option value="2">每周推荐</option>
                                <option value="3">攻略推荐</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveCommendBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>