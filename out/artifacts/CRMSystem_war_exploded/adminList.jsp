<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" action="queryAdminByWhere.do?currentPage=1&pageSize=3" method="post">

                        <div class="layui-inline layui-show-xs-block">
                            <input class="layui-input"  autocomplete="off" placeholder="开始日" name="start" id="start" value="${start}">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input class="layui-input"  autocomplete="off" placeholder="截止日" name="end" id="end" value="${end}" }>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input" value="${username}">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <input id="ids" name="ids" type="hidden" lay-filter="ids">
                    <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
                    <button class="layui-btn" onclick="xadmin.open('添加用户','./addAdmin.do',600,400)"><i class="layui-icon"></i>添加</button>
                    <select lay-filter="sizeSel" id="sizeSel" onchange="changeSize(this)">
                        <option value="3" <c:if test="${adminInfo.pg.pageSize eq 3}">selected</c:if> >3</option>
                        <option value="5" <c:if test="${adminInfo.pg.pageSize eq 5}">selected</c:if> >5</option>
                        <option value="10" <c:if test="${adminInfo.pg.pageSize eq 10}">selected</c:if> >10</option>
                        <option value="20" <c:if test="${adminInfo.pg.pageSize eq 20}">selected</c:if> >20</option>
                    </select>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                <input id="allCheck" type="checkbox" name="" lay-filter="allCheck" lay-skin="primary">
                            </th>
                            <th>ID</th>
                            <th>登录名</th>
                            <th>手机</th>
                            <th>密码</th>
                            <th>年龄</th>
                            <th>角色</th>
                            <th>加入时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${adminInfos}" var="admin">
                            <tr>
                                <td>
                                    <input id="ch_${admin.id}"  type="checkbox" lay-filter="oneCheck" name="chname"  lay-skin="primary" value="${admin.id}">
                                </td>
                                <td>${admin.id}</td>
                                <td>${admin.acount}</td>
                                <td>${admin.phone}</td>
                                <td>*********</td>
                                <td>${admin.age}</td>
                                <td>超级管理员</td>
                                <td>${admin.createtime}</td>
                                <td class="td-status">
                                    <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td>
                                <td class="td-manage">
                                    <a onclick="member_stop(this,'10001')" href="javascript:;"  title="启用">
                                        <i class="layui-icon">&#xe601;</i>
                                    </a>
                                    <a title="编辑"  onclick="xadmin.open('编辑','editJump.do?id=${admin.id}')" href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="member_del(this,'${admin.id}')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
                <div class="layui-card-body ">
                    <div class="page">
                        <div>
                            <a class="prev" href="queryAdminByWhere.do?currentPage=1&pageSize=${adminInfo.pg.pageSize}&username=${username}&start=${start}&end=${end}">首页</a>
                            <a class="prev" href="queryAdminByWhere.do?currentPage=${adminInfo.pg.prevPage}&pageSize=${adminInfo.pg.pageSize}&username=${username}&start=${start}&end=${end}">&lt;&lt;</a>
                            <c:forEach var="index" begin="1" end="${adminInfo.pg.pageCount}" >
                                <c:if test="${adminInfo.pg.currentPage eq index}">
                                    <span class="current">${index}</span>
                                </c:if>
                                <c:if test="${adminInfo.pg.currentPage != index}">
                                    <a class="num" href="queryAdminByWhere.do?currentPage=${index}&pageSize=${adminInfo.pg.pageSize}&username=${username}&start=${start}&end=${end}">${index}</a>
                                </c:if>
                            </c:forEach>
                            <a class="next" href="queryAdminByWhere.do?currentPage=${adminInfo.pg.nextPage}&pageSize=${adminInfo.pg.pageSize}&username=${username}&start=${start}&end=${end}">&gt;&gt;</a>
                            <a class="next" href="queryAdminByWhere.do?currentPage=${adminInfo.pg.pageCount}&pageSize=${adminInfo.pg.pageSize}&username=${username}&start=${start}&end=${end}">尾页</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['laydate','form'], function(){
        var laydate = layui.laydate;
        var form = layui.form;

        //给复选框绑定修改的事件
        form.on('checkbox(allCheck)',function (data) {
            //遍历所有的子的复选框;勾选
            var child = $("input[name='chname']");

            var ids = '';
            //遍历每个子复选框,全选
            child.each(function(index,item){
                item.checked = data.elem.checked;

                if(data.elem.checked){
                    ids += $(item).val() + ',';
                }
            })

            $("#ids").val(ids);//需要删除的id传递到后台去

            form.render('checkbox');//刷新一下它的样式
        })

        //给每一个子选项的复选框绑定事件
        form.on('checkbox(oneCheck)',function (data) {
            //如何判断我们的全选框应该是选中还是不选中
            var child1 = $("input[name='chname']").length;
            //选中的复选框有几个
            var child2 = $("input[name='chname']:checked").length;

            var ids = '';
            $("input[name='chname']:checked").each(function(){
                ids += $(this).val() + ",";
            })
            $("#ids").val(ids);//需要删除的id传递到后台去

            $("#allCheck").prop("checked",child1 == child2);//如果子选项的个数和选中的个数一样;需要全选
            form.render('checkbox');//刷新一下他的样式
        })

        //执行一个laydate实例
        laydate.render({
            elem: '#start' ,//指定元素
            type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' ,//指定元素
            type:'datetime'
        });
    });

    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){

            if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

            }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){

            $.ajax({
                url:"deleteAdmin.do",
                type:"post",
                data:{"id":id},
                success:function (data) {
                    if (data === "1111"){
                        //发异步删除数据
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!',{icon:1,time:1000});
                    }

                }
            })


        });
    }



    function delAll (argument) {

        if ($("input[name='chname']:checked").length <= 0){
            layer.alert("你没有选择需要删除的内容");
        }else{
            layer.confirm('确认要删除这些记录吗？',function(index){
                $.ajax({
                    url:"delAdminByIds.do",
                    type: "post",
                    data:{"ids":$("#ids").val()},
                    success:function (result) {
                        if (result === "1111"){
                            //捉到所有被选中的，发异步进行删除
                            layer.msg('删除成功', {icon: 1});
                            $(".layui-form-checked").not('.header').parents('tr').remove();
                        }
                    }
                })

            });
        }


    }

    function changeSize(obj) {
        location.href="queryAdminByWhere.do?currentPage=1&username=${username}&start=${start}&end=${end}&pageSize="+$(obj).val();
    }
</script>

</html>