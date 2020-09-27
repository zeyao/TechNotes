## Git 使用场景

### 怎么把提交的几个command 合并成一个呢？

- 1: git git rebase -i HEAD~2 

- 2: 可以看到有两个最近的commit :

   - pick ff2d20d task on board
   - pick 38c3212 test2
   
   - 把test2 squash:
   
       - terminal中选择i 更改：
   
       - **squash** 38c3212 test2
   
   - Esc :wq 推出
   
   - 然后 git push origin +brunchname 同步到repo
   
   
   
   