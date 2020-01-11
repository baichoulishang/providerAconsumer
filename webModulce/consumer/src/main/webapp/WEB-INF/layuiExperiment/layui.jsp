<%@ page import="java.util.Set" %>
<%@ page import="cn.hutool.core.date.DateTime" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("ctx", request.getContextPath());%>
<html>
<head>
    <link rel="stylesheet" href="${ctx}/core/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<form action="${ctx}/file/uploadMultipartFile.do"
      method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="提交">
</form>


<button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>

<script src="${ctx}/core/js/jquery-3.3.1.min.js?ver=<%=DateTime.now()%>"></script>
<script src="${ctx}/core/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

    $(function () {
        layui.use('upload', function () {
            var $ = layui.jquery
                , upload = layui.upload;
            //指定允许上传的文件类型
            upload.render({
                elem: '#test3'
                , url: '${ctx}/file/uploadMultipartFile.do'
                , accept: 'file' //普通文件
                , done: function (res) {
                    console.log(res)
                }
            });

        });
    });
</script>

</body>
</html>