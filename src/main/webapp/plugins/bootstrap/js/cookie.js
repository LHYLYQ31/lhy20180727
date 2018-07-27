/*********************
 * 通过后台设置的cookie在同服务器下的项目可以共同使用
 * 通过js设置的cookie只能在其项目中使用
 * 
 *********************/           


            /* 通过后台设置cookie
			*key cookie的name
			*val cookie的value
			成功返回true  失败返回false
			*/
			function setcookie(key,val){
				var result = false;
				$.ajax({
						type: 'post',
						url: basePath + '/cookie/add',
						async: false,
						data:{
							cookiename:key,
							cookievalue:val
						},
						success: function() {
							result = true;
						},
						error:function(){
							result = false;
						}
				});
				return result;
			}
			/*获取通过后台设置的cookie
			*key 要获取的cookie的name
			*成功返回对应的value 如果没有对应的cookie则返回""
			*失败返回null
			*/
			function getcookie(key){
				var result = null;
				$.ajax({
					type: 'post',
					url: basePath + '/cookie/search',
					async: false,
					data:{
						cookiename:key
					},
					success: function(data) {
						result = decodeURIComponent(data);
					},
					error:function(){
						result = null;
					}
			    });
				return result;
			}
			/*删除通过后台设置的cookie
			*key 要删除的cookie的name
			*删除成功返回true
			*删除失败返回false
			*/
			function deletecookie(key){
				var result = false;
				$.ajax({
					type: 'post',
					url: basePath + '/cookie/delete',
					async: false,
					data:{
						cookiename:key
					},
					success: function(data) {
						 result =  true;
					},
					error:function(){
						result = false;
					}
			    });
				return result;
			}
			
			/*获取js 设置的cookie值 
			 *key cookie的name，
			 *返回 key对应的value值，没有对应的cookie时返回null
			*/
			function getCookie(key){
				
			if (document.cookie.length>0){
			  c_start=document.cookie.indexOf(key + "=")
			  if (c_start!=-1){ 
			    c_start=c_start + key.length+1 ;
			    c_end=document.cookie.indexOf(";",c_start);
			    if (c_end==-1) {
			    	c_end=document.cookie.length;
			    }
			    return unescape(document.cookie.substring(c_start,c_end));
			    } 
			  }
			return null;
			}
            /* js设置cookie 
             * key cookie的名字，取cookie时通过key找对应的value
             *value  cookie中要存的值 
             *extime  cookie存活时间 以分钟为单位
            */
			function setCookie(key,value,extime)
			{
			var exp=new Date();
			exp.setTime(exp.getTime()+(extime*60 * 1000));
			document.cookie = key + "=" + escape(value) + ";expires=" + exp.toGMTString();
			}
			/* 清除js cookie */
			function clearCookie(key){
				setCookie(key, "", -1);
			}