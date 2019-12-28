# 最大流 Ford-Fullerson 

- 寻找增广路，经过start - end 无法开辟出一条新的增广路，说明饱和
- 每一条增广路都有bottleneck, 这个value 就是增广路的 value
- 每次 找到增广路，更新已有边的 capacity, 并且创建逆向流的边
- 这个逆向流用于之后撤销不能达到最大流选择 ( 使用边冲突 ）的一个技巧，下面会解释


<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/FordFullerson1.jpg" style="height:500px" />

-

<img src="https://raw.githubusercontent.com/zeyao/TechNotes/master/Document/FordFullerson2.jpg" style="height:500px" />