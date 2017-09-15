<#include '../common/macro.ftl'>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

Table test data:


<@paginate "/personal/manage/proposals?sort=", 2, 21, 11/>
</body>
</html>