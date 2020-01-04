<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("ctx", request.getContextPath());%>
<html>
<head>
    <title>第一个Vue程序</title>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <%--    <script src="${ctx}/core/js/vue.js"></script>--%>
</head>

<body>
<div id="app">
    {{ message }}
</div>

<div id="app-2">
  <span v-bind:title="message">
    鼠标悬停几秒钟查看此处动态绑定的提示信息！
  </span>
</div>

</body>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: '2019-9-7 20:02:48'
        }
    });


    var app2 = new Vue({
        el: '#app-2',
        data: {
            message: '页面加载于 ' + new Date().toLocaleString()
        }
    });
</script>
</html>
