<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("ctx", request.getContextPath());%>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="${ctx}/core/layui/css/layui.css" media="all">
<body>
<form class="layui-form" action="${ctx}/role/add.do">
    <div class="layui-form-item">
        <label class="layui-form-label">单行输入框222</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单行输入框</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单行输入框</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>

<script src="${ctx}/core/js/jquery-3.3.1.min.js"></script>
<script src="${ctx}/core/layui/layui.all.js" charset="utf-8"></script>

<script>


</script>
</html>
