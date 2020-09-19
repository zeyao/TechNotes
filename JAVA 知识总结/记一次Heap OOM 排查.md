#### 记一次OOM排查

 - Lengency系统里有一个service 会不时报错，有时好有时坏
 - 后台系统log发现有heap OOM 的问题
 - request heap dump
 - 查看发现有二十个entry占用了大概75%的heap size
 - 发现这entry都是较大rest response的payload object， 并且是frmk level的一个cacheRepalyInputSteam ，前后keep了大概一个小时的cache;
 - 和frmk team的人询问，disable cache -> done

 
