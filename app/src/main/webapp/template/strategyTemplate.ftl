<div class="container">
    <h6>当季推荐</h6>
    <div class="row hot">
    <#if hots??>
        <#list hots as hot>
        <div class="col ">

            <a href="../strategyCatalogs.html?id=${hot.id}">
                <img src="${hot.coverUrl}">
                <p>0次浏览</p>
            </a>
        </div>
        </#list>
    </#if>
    </div>
</div>
    <hr>
<div class="container">
    <h6>全部攻略</h6>
    <div class="row classify ">
    <#if alls??>
        <#list alls as all>
        <div class="col-6 mb">
            <a href="../strategyCatalogs.html?id=${all.id}">
                <img class="float-left " src="${(all.coverUrl)!}">
                <div class="classify-text">
                    <span>${all.title}</span>
                    <p>0人收藏</p>
                </div>
            </a>
        </div>
        </#list>
    </#if>
    </div>
</div>