<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl">
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

<#--引入ckeditor插件-->
    <script type="text/javascript" src="/js/plugins/ckeditor/ckeditor.js"></script>
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
        $("#addStrategyDetailBtn").click(function () {


            $("#strategyModal").modal("show");
        });

        //二级联动
        $("#strategySelect").change(function () {
            $.get("/strategyCatalog/catalogs?strategyId=" + $(this).val(), function (data) {

            });
        });
    });
</script>

<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "strategyDetail"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略文章管理</h1>
                </div>
            </div>

            <!-- 提交分页的表单 -->
            <form id="searchForm" class="form-inline" method="post" action="/strategyDetail/list.do">
                <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                <div class="form-group">
                </div>
                <div class="form-group">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入需要所搜的标题">
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    <a href="javascript:void(-1);" class="btn btn-success" id="addStrategyDetailBtn">添加推荐游记</a>
                </div>
            </form>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>发布时间</th>
                    <th>排序</th>
                    <th>攻略类别</th>
                    <th>状态</th>
                    <th></th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${(entity.title)!}</td>
                        <td><img src="${(entity.coverUrl)!}" style="width: 60px"></td>
                        <td>${(entity.releaseTime?string("yyyy-MM-dd"))!}</td>
                        <td>${(entity.sequence)!}</td>
                        <td>${(entity.catalog.name)!}</td>
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



<#--攻略文章管理-->
<div id="strategyModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal " method="post" enctype="multipart/form-data"
                      action="/strategyDetail/saveOrUpdate.do" style="margin: -3px 20px">
                    <input id="id" type="hidden" name="id" value=""/>
                    <div class="form-group inline">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title" name="title" placeholder="攻略标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img id="coverUrl" width="200px"/>
                            <input type="file" class="form-control" id="pic" name="pic">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略</label>
                        <div class="col-sm-6">
                            <select id="strategySelect" name="strategy.id" class="form-control form-control-chosen"
                                    data-placeholder="请选择所属攻略">
                                <#list strategies as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">攻略类别</label>
                        <div class="col-sm-6">
                            <select id="catalogSelect" name="catalog.id" class="form-control form-control-chosen"
                                    data-placeholder="请选择攻略类别">
                                <option></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">是否发布</label>
                        <div class="col-sm-6">
                            <select id="isRelease" class="form-control" autocomplete="off" name="isRelease">
                                <option value="true">是</option>
                                <option value="false">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">排序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sequence" name="sequence" placeholder="排序">
                        </div>
                    </div>
                    <textarea id="content" name="content" class="ckeditor" cols="10" rows="10">
                      </textarea>
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