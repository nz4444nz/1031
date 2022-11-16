<%--
  Created by IntelliJ IDEA.
  User: 牛壮
  Date: 2022/9/26
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>

</head>
<body>
<center>
    <h2>现在由你增加车辆</h2>
    <form action="../car/addCar.do" method="post">
        车名:<input type="text" name="carname"><br/><br/>
        时间:<input type="date" name="buydate"><br/><br/>
        地点:<input type="text" name="address" id="address"><br/><br/>
        选择:
                <select id="proselect" onchange="changepro(this)">
                <option value ="-1">--请选择--</option>
                <c:forEach var="p" items="${plist}">
                <option value ="${p.id}">${p.cityname}</option>
                </c:forEach>
                </select>--
                <select id ="cityselect" onchange="changecity(this)">
                <option value ="-1">--请选择--</option>
                </select>
                <select id ="couselect" onchange="changecou(this)">
                <option value ="-1">--请选择--</option>
                </select>
        <br/><br/>
        <input type="file" id="filename" name="filename"/><br>
        <input type="button" value="上传" onclick="fileupload()"/>
        <img id="fileimg" width="200px" height="200px">
        <input type="hidden" name="userurl"/>
        <br/><br/>
        颜色:<select name="cid">
            <option>-请选择-</option>
            <c:forEach items="${clist}" var="c">
                <option value="${c.cid}">${c.cname}</option>
            </c:forEach>
        </select><br/><br/>
        <input type="submit" value="提交">
    </form>
</center>
<script>
    var proname="";
    var cityname="";
    var couname="";
    var p="--请选择--"
    function changepro(obj){
        proname=$("#proselect option:selected").text();
        $("#address").val(proname)
        $.post("../car/getCityListByPid.do",{"pid":obj.value},function(response){
            $("#cityselect").html("<option value='-1'>--请选择--</option>")
            $("#couselect").html("<option value='-1'>--请选择--</option>")
            for(var x=0;x<response.length;x++){
                $("#cityselect").append("<option value='"+response[x].id+"'>"+response[x].cityname+"</option>")
            }
        })
    }
    function changecity(obj){
        cityname=$("#cityselect option:selected").text();
        $("#address").val(proname+cityname)
        $.post("../car/getCityListByPid.do",{"pid":obj.value},function(response){
            $("#couselect").html("<optionvalue='-1'>--请选择--</option>")
            for(var x=0;x<response.length;x++){
                $("#couselect").append("<option value='"+response[x].id+"'>"+response[x].cityname+"</option>")
            }
        })
    }
    function changecou(){
        couname=$("#couselect option:selected").text();
        $("#address").val(proname+cityname+couname)
    }

    function fileupload() {
        var files = $('#filename')[0].files;
        if(files.length <=0 ){
            return alert('请选择文件后再上传！')
        }
        var fd = new FormData()
        fd.append('filename',files[0]);
        $.ajax({
            type:'post',
            url:'${pageContext.request.contextPath}/car/fileUpload.do',
            data:fd,
            dataType: "json",
            processData:false,
            contentType:false,
            success:function(response){
                if(response.flag){
                    document.getElementById("fileimg").src = "http://localhost:8088/niuzhuang_1031A"+response.msg; //
                    document.getElementsByName("userurl")[0].value = "http://localhost:8088/niuzhuang_1031A"+response.msg; //将回显的数据添加到input框里，将
                }else {
                    alert(response.msg);
                }
            }
        })
    }
</script>
</body>
</html>
