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

        //推荐文章 初始化
        $(".btn-look").click(function () {
            var json = $(this).data("json");
            console.log(json);
            $("[name='id']").val(json.id);
            $("[name=title]").val(json.title);
            $("[name=subTitle]").val(json.subTitle);
            $("#coverUrl").attr("src", json.coverUrl);
            $("[name=schedule]").val(json.schedule);
            $("select[name=type]").val(json.type);

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

        //新增推荐文章
        $("#addTravelCommendBtn").click(function () {
            $("#commendForm > div:last-child").empty();
            $("#commendForm").append('<div class="form-group" style="text-align: center">\n' +
                    '<a href="javascript:void(0)" target="_blank" id="skip">点击查看游记文章明细</a>\n' +
                    '</div>');
            $("#travelCommendModal").modal("show");
        });
    });
</script>

<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "travelCommend"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">游记推荐管理</h1>
                </div>
            </div>

            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/travelCommend/list.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!}">
                </div>
                <div class="form-group">
                    <select id="qo_type" class="form-control" autocomplete="off" name="type">
                        <option value="-1">全部</option>
                        <option value="1">每月推荐</option>
                        <option value="2">每周推荐</option>
                        <option value="3">攻略推荐</option>
                    </select>
                    <script type="text/javascript">
                        $("select[name=type]").val(${(qo.type)!});
                    </script>
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    <a href="javascript:void(-1);" class="btn btn-success" id="addTravelCommendBtn">添加推荐游记</a>
                </div>
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>封面</th>
                    <th>标题</th>
                    <th>副标题</th>
                    <th>推荐时间</th>·
                    <th>推荐类型</th>
                    <th></th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td><img src="${(entity.coverUrl)!}" style="width: 50px;"/></td>
                        <td>${(entity.title)!}</td>
                        <td>${(entity.subTitle)!}</td>
                        <td>${(entity.schedule?string("yyyy-MM-dd"))!}</td>
                        <td>${(entity.types)!}</td>
                        <td>
                            <a class="btn btn-info btn-xs btn-look" href="javascript:;" data-json='${(entity.json)!}'>
                                <span class="glyphicon glyphicon-pencil "></span>修改</a>
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