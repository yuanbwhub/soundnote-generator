<%--
  Created by IntelliJ IDEA.
  User: abim
  Date: 2018/1/5
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <style>
        * {
            margin: 0px;
            padding: 0px;
        }

        select {
            width: 100px;
            height: 30px;
            padding-left: 10px;
            font-size: 16px;
        }

        div {
            width: 500px;
            height: 300px;
            margin: 0px auto;
            margin-top: 200px;
            text-align: center;
        }

        span {
            display: inline-block;
            margin-top: 20px;
            text-align: center;
            font-size: 15px;
            color: #555555;
        }
    </style>
    <script src="../js/jquery-3.0.0.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn").click(function () {
                alert("生成中，可能会花一定时间...");
                alert("请不要再次点击按钮！完成后会提示。");
                $.ajax({
                    url: "getCode?codenumber=" + $("#codenumber").val(),
                    type: "post",
                    dataType: "json",
                    success: function (e) {
                        if (e.result == "0") {
                            alert("生成失败！");
                        } else if (e.result == "1") {
                            alert("生成成功！请至默认目录查看");
                        }
                    }
                });
            });
        })
        ;
    </script>
</head>
<body>
<div>
    生成二维码个数选择：
    <select id="codenumber">
        <option value="0">请选择</option>
        <option value="1">1</option>
        <option value="5">5</option>
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="30">30</option>
        <option value="40">40</option>
        <option value="50">50</option>
    </select>
    <input type="button" value="生成" id="btn" style="width: 60px;height: 30px;">
    <br/>
    <span>(默认保存在E:/微信小程序/  目录下)</span>
</div>
</body>
</html>
