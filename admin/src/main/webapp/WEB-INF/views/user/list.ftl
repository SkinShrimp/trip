<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl">
</head>

<script type="text/javascript">
    $(function () {
    });
</script>

<body>

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
                    <h1 class="page-head-line">平台用户管理</h1>
                </div>
            </div>

            <form class="form-inline" id="searchForm" action="/user/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">

                <div class="form-group">
                    <label>关键字</label>
                    <input class="form-control" type="text" name="keyWord" value="${(qo.keyWord)!}"
                           placeholder="请输入邮箱/昵称">
                </div>
                <button id="query" class="btn btn-success"><i class="icon-search"></i> 查询</button>
            </form>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>邮箱</th>
                    <th>昵称</th>
                    <th>地区</th>
                    <th>头像</th>
                    <th>性别</th>
                    <th>签名</th>
                </tr>
                </thead>
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${(entity.email)!}</td>
                        <td>${(entity.nickName)!}</td>
                        <td>${entity.place}</td>
                        <td><img src="${(entity.headimgurl)!}" style="width: 40px"/></td>
                        <td>${entity.genders}</td>
                        <td>${entity.sign}</td>
                    </tr>
                </#list>
            </table>

            <div style="text-align: center;">
                <#include "../common/page.ftl">
            </div>
        </div>
    </div>
</div>

</body>
</html>