<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl">
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

</head>

<script type="text/javascript">
    $(function () {

        //推荐文章 初始化
        $(".btn-look").click(function () {

            var json = $(this).data("json");
            console.log(json);
            $("[name='id']").val(json.id);
            $("[name=name]").val(json.name);
            $("[name='strategy.id']").val(json.strategyId);
            $("input[name=sequence]").val(json.sequence);
            $("select[name=state]").val((json.state + ""));

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
        <#assign currentMenu = "strategyCatalog"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略分类管理</h1>
                </div>
            </div>

            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/strategyCatalog/list.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <label>攻略搜索</label>
                    <select class="form-control" name="strategyId">
                        <option value="-1">请选择攻略</option>
                        <#list strategys as strategy>
                            <option value="${strategy.id}">${strategy.title}</option>
                        </#list>
                    </select>
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
                    <th>分类名称</th>
                    <th>所属攻略</th>
                    <th>排序</th>
                    <th>状态</th>
                    <th></th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${(entity.name)!}</td>
                        <td>${(entity.strategy.title)!}</td>
                        <td>${(entity.sequence)!}</td>
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



<#--攻略分类-->
<div id="travelCommendModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="commendForm" class="form-horizontal" method="post" action="/strategyCatalog/saveOrUpdate.do"
                      enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="travelCommendId" type="hidden" name="id" value=""/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">分类名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" placeholder="名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="strategy.id">
                                <#list strategys as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">排序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence" placeholder="排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state">
                                <option value="true">启用</option>
                                <option value="false">禁用</option>
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