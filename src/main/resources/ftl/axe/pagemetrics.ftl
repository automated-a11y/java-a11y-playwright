<div class="row">
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info"><i class="fas fa-universal-access text-warning"></i><span
                    class="text-warning"> Engine</span> : ${testRunner.name?cap_first}</h6>
        <hr/>
    </div>
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info"><i class="fas fa-code-branch text-warning"></i><span
                    class="text-warning"> ${testRunner.name?cap_first} version</span> : ${testEngine.version}</h6>
        <hr/>
    </div>
</div>
<div class="row">
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info text-truncate"><i class="fas fa-external-link-alt text-warning"></i><span
                    class="text-warning"> Url</span> : ${url}</h6>
        <hr/>
    </div>
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info"><i class="fas fa-list-ul text-warning"></i><span class="text-warning"> Standard(s)</span>
            : <#list toolOptions.runOnly.values as value><span class="badge badge-pill badge-info">${value}</span></#list></h6>
        <hr/>
    </div>
</div>
<div class="row">
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info">
            <i class="far fa-credit-card text-warning"></i><span
                    class="text-warning"> Browser</span>
            : ${browser}</h6>
        <hr/>
    </div>
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info"><i class="fas fa-arrows-alt text-warning"></i><span
                    class="text-warning"> Browser size</span> : ${dimension}</h6>
        <hr/>
    </div>
</div>
<div class="row">
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info"><i class="far fa-window-restore text-warning"></i><span
                    class="text-warning"> Device</span> : ${device}</h6>
        <hr/>
    </div>
    <div class="col-lg-6 col-md-6">
        <h6 class="text-info"><i class="fas fa-clock text-warning"></i><span
                    class="text-warning"> Timestamp</span> : ${date}</h6>
        <hr/>
    </div>
</div>
<#assign array = (violations+incomplete)?filter(x -> x.impact??)>
<#assign criticals =array?filter(x -> x.impact == 'critical')>
<#assign seriouss =array?filter(x -> x.impact == 'serious')>
<#assign moderates =array?filter(x -> x.impact == 'moderate')>
<#assign minors =array?filter(x -> x.impact == 'minor')>
<#assign criticalcount=0 seriouscount=0 moderatecount=0 minorcount=0>
<#list criticals as item>
    <#assign criticalcount += item.nodes?size>
</#list>
<#list seriouss as item>
    <#assign seriouscount += item.nodes?size>
</#list>
<#list moderates as item>
    <#assign moderatecount += item.nodes?size>
</#list>
<#list minors as item>
    <#assign minorcount += item.nodes?size>
</#list>
<#assign violations_count = criticalcount+seriouscount+moderatecount+minorcount>

<div class="row">
    <div class="col-lg-3 col-md-3 pillspage badge badge-danger">
        <div class="section">
            <h6>Critical</h6>
            <h1> ${criticalcount}</h1>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 pillspage badge badge-warning">
        <div class="section">
            <h6>Serious</h6>
            <h1> ${seriouscount}</h1>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 pillspage badge badge-info">
        <div class="section ">
            <h6>Moderate</h6>
            <h1> ${moderatecount}</h1>
        </div>
    </div>
    <div class="col-lg-3 col-md-3 pillspage badge badge-light">
        <div class="section ">
            <h6>Minor</h6>
            <h1> ${minorcount}</h1>
        </div>
    </div>
</div>