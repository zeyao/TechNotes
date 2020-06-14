func twoSum(nums []int, target int) []int {
    for i := 0; i < len(nums); i++ {
    	for j := i+1; j < len(nums); j++ {
    		sum := nums[i] + nums[j];
            if (sum == target) {
                return []int{i, j} 
    		}
    	}

    }
    return []int{};
}

func twoSum(nums []int, target int) []int {
    dic := make(map[int]int)
	for i:= range nums {
		index, ok := dic[target - nums[i]]
		if (ok) {
			return []int{i, index};
		}
		dic[nums[i]] = i;
	}
	return []int{};
}
