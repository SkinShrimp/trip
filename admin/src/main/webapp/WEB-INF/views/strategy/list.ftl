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
            $("input[name=coverUrl]").val(json.coverUrl);
            $("select[name='place.id']").val(json.placeId);
            $("select[name=state]").val(json.state);

            $("#travelCommendModal").modal("show");
        });

        //推荐文章 保存
        $("#saveCommendBtn").click(function () {
            $.messager.confirm("温馨提示", "确定提交攻略?", function () {
                $("#commendForm").ajaxSubmit(function (data) {
                    if (data.success) {
                        successAlert(data);
                    }
                });
            });
        });

        //新增推荐文章
        $("#addTravelCommendBtn").click(function () {
            $("#commendForm")[0].reset();


            $("#travelCommendModal").modal("show");
        });
    });
</script>

<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "strategy"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略管理</h1>
                </div>
            </div>

            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/strategy/list.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入攻略标题">
                </div>
                <div class="form-group">
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    <a href="javascript:void(-1);" class="btn btn-success" id="addTravelCommendBtn">添加推荐游记</a>
                </div>
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>封面</th>
                    <th>攻略标题</th>
                    <th>副标题</th>
                    <th>所属地区</th>
                    <th>状态</th>
                    <th></th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td><img src="${(entity.coverUrl)!}" style="width: 50px;"/></td>
                        <td>${(entity.title)!}</td>
                        <td>${(entity.subTitle)!}</td>
                        <td>${(entity.place.name)!}</td>
                        <td>${(entity.stateName)!}</td>
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



<#--推荐-->
<div id="travelCommendModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">修改攻略</h4>
            </div>
            <div class="modal-body">
                <form id="commendForm" class="form-horizontal" method="post" action="/strategy/saveOrUpdate.do"
                      enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="travelCommendId" type="hidden" name="id" value=""/>
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
                        <label class="col-sm-4 control-label">所属地区</label>
                        <div class="col-sm-6">
                            <select class="form-control" autocomplete="off" name="place.id">
                                <#list regions as region>
                                    <option value="${region.id}">${region.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state">
                                <option value="0">正常</option>
                                <option value="1">热门</option>
                                <option value="-1">禁用</option>
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