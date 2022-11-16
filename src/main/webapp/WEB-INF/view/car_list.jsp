<%--
  Created by IntelliJ IDEA.
  User: 牛壮
  Date: 2022/9/26
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2>车辆展示列表</h2>
    <form action="../car/findAll.do" method="post">
        <input type="hidden" name="pageNum">
        按照产地查询:<input type="text" name="address" value="${carBean.address}"><br/>
        按照时间查询:<input type="date" name="buydate" value="<fmt:formatDate value="${carBean.buydate}" pattern="yyyy-MM-dd"/>">--至--
        <input type="date" name="edate" value="<fmt:formatDate value="${carBean.edate}" pattern="yyyy-MM-dd"/>">
        <input type="submit" value="检索">
    </form>
        <table border="1" rules="all">
            <tr align="center">
                <th width="100px">编号</th>
                <th width="100px">车名</th>
                <th width="100px">时间</th>
                <th width="100px">地点</th>
                <th width="100px">图片</th>
                <th width="100px">颜色</th>
                <th width="100px">操作<button onclick="toAddCar()">添加</button>
                </th>
            </tr>
            <c:forEach items="${pageInfo.list}" var="c" varStatus="index">
                <tr align="center">
                    <td>${index.count}</td>
                    <td>${c.carname}</td>
                    <td><fmt:formatDate value="${c.buydate}" pattern="yyyy-MM-dd"/></td>
                    <td>${c.address}</td>
                    <td><img src="${c.userurl}" width="60px" height="60px"></td>
                    <td>${c.cb.cname}</td>
                    <td>
                        <button onclick="deleteCar(${c.carid})">删除</button>
                    </td>
                </tr>
            </c:forEach>
            <tr align="center">
                <td colspan="20">
                    当前${pageInfo.pageNum}/${pageInfo.pages}页.总共${pageInfo.total}条
                    <button onclick="paging(1)">首页</button>
                    <button onclick="paging(${pageInfo.prePage})">上一页</button>
                    <button onclick="paging(${pageInfo.nextPage})">下一页</button>
                    <button onclick="paging(${pageInfo.lastPage})">尾页</button>
                </td>
            </tr>
        </table>
</center>
<script>
    function paging(pageNum) {
        if (pageNum == 0){
            pageNum = 1
        }
        document.getElementsByName("pageNum")[0].value = pageNum
        document.forms[0].submit()

    }
    function deleteCar(id) {
        if(confirm("您确定要删除该条记录吗?")){
            location.href="../car/deleteCar.do?id="+id;
        }
    }
    function toAddCar() {
            location.href="../car/toAddCar.do";
    }
</script>

</body>
</html>
