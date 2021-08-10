<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加角色</title>
    <link rel="stylesheet" href="./zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="./js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="./js/jquery.ztree.all.min.js"></script>
</head>
<body>
    <form action="addRole.do" method="post">
        角色名称：<input type="text" name="roleName"><br/>

        <input type="hidden" name="auths" />
        权限:<ul id="treeDemo" class="ztree"></ul>

        <hr/>
        <br/>
        角色描述:
        <textarea name="roleDesc"></textarea>
        <br/>

        <button type="button">添加角色</button>
    </form>

</body>
<SCRIPT LANGUAGE="JavaScript">
    var zTreeObj;
    // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
    var setting = {
        check: {
            enable: true,
            autoCheckTrigger: true
        },
        data: {
            key: {
                name: "aname"
            },
            simpleData: {
                enable: true,
                idKey: "aid",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        callback: {
            //选中的事件
            beforeCheck: function(treeId, treeNode){

            }
        }

    };

    //初始化树节点
    //setting:对于树的一些配置信息
    //zNodes:数据节点信息
    $(document).ready(function(){

        $.ajax({
            url:"queryAuths.do",
            type:"post",
            success:function(data){

                console.info(data);

                // var zn = [{"aid":1,"aname":"整个系统","apath":"/","atype":1,"pid":0},{"aid":2,"aname":"管理员管理","apath":"/admin.lt","atype":2,"pid":1},{"aid":3,"aname":"客户管理","apath":"/customer.lt","atype":2,"pid":1},{"aid":4,"aname":"销售管理","apath":"/sell.lt","atype":2,"pid":1},{"aid":5,"aname":"管理员添加","apath":"/adminAdd.lt","atype":3,"pid":2},{"aid":6,"aname":"管理员删除","apath":"/adminDel.lt","atype":3,"pid":2},{"aid":7,"aname":"管理员修改","apath":"/adminUpd.lt","atype":3,"pid":2},{"aid":8,"aname":"管理员查询","apath":"/adminSel.lt","atype":3,"pid":2},{"aid":9,"aname":"管理员列表","apath":"/adminList.lt","atype":3,"pid":2}];

                var zn = eval("(" + data + ")" );

                //加载数据后,再去初始化我们的树节点
                zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zn);//data:就是我们从后台请求来的数据
            }
        })

        $("button").click(function(){

            var zt = zTreeObj.getCheckedNodes(true);

            var ids = '';
            for(var index in zt){
                ids += zt[index].aid + ",";
            }

            $("input[name='auths']").val(ids);

            $("form").submit();

        })

    });
</SCRIPT>
</html>
