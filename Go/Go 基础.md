# Go 语言基础


## 基础语法
### Array
- new array

    ```
    arr := make([]int, 10)
    
    ```
    
- String to byte array
 	
   ```
    arr := []byte(str)
    
   ```
 
 
    
### Map
 - new Map
 
    ```
     dic := make(map[int]int)
    
    ```
     
 - 检查是否存在元素：
   
   ``` 
     _, ok := dic[num]
     if ok {
      XXX
     } else {
     
     }
     ```
- put in map：

   ``` 
    dic := make(map[string]int)
    str := "a"
	
	dic[str]++
	   
   ``` 
     
### Slice 
 - new Slice：
 
    ```
    res := make([]string, 0)
    
    ```
 - add elemet to slice：   
   
   ```
    res = append(res, "dummystring")
   
   ```
   
 - sort slice：   

   ```
 	words := make([]string, 0)
 	words = append(words, "a") // append alot ....
 	
 	//用 sort.SliceStable 方法，sort.SliceStable 可以保持元素顺序和之前切片中的一样。
	// 用 sort.Slice会失败
	
 	sort.SliceStable(words, func(i, j int) bool {
		return len(words[i]) < len(words[j])
	})
 
   ```
 
 - string and slice： 

   ```
    words := strings.Split(str, " ")
    //string to slice
 
    ans := strings.Join(words, " ")
    //slice to string
  
   ```
 
 
### stirng 

- string to int, 第一个返回可以转换成的数字，err == nil 说明可以转换
 
   ```
   num, err := strconv.Atoi(input)
 
   if err == nil {
 		// do something
   }
 
 
   b, err := strconv.ParseBool("true")
   f, err := strconv.ParseFloat("3.1415", 64)
   i, err := strconv.ParseInt("-42", 10, 64)
   u, err := strconv.ParseUint("42", 10, 64)
 
   ```
 
- int to string 
  
   ```

	s := strconv.Itoa(-42)
	
	s := strconv.FormatBool(true)
   s := strconv.FormatFloat(3.1415, 'E', -1, 64)
   s := strconv.FormatInt(-42, 16)
   s := strconv.FormatUint(42, 16)

   ```
 

 
- substring 方法
 
   ```
	 l := str[0:i] // [0, i) substirng
	 r := str[i+1:] // [i+1, len(str))
  
   ```
  
- String to byte array
 	
  ```
   arr := []byte(str)
    
  ```
 
- string is immutable

  ```
	 str := "adasdasd"
	 c := str[1] // 相当于 java charAt, 但是不可以直接在string修改 不可以 str[1] = 's'
	
  ```

- stringbuilder

    ```
    var sb strings.Builder
  
  
    for i := range arr {
		sb.WriteString(strconv.Itoa(arr[i]))
    }
  
    str := sb.String()
	
	
	```

- to lower upper case
  
   ```
	strings.ToLower(text)
	
   ```
### struct 
 
   ```
 
	 type entry struct {
		key int;
		count int;
	 }
	 
	 entrySet := []entry{}
	 entrySet = append(entrySet, entry{key: 1, count: 1})
	 
	   
 

  ```