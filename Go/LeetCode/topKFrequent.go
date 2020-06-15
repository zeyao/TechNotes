package main

import "fmt"
import "sort"

func main() {
	
}

func topKFrequent(nums []int, k int) []int {
	dic := make(map[int]int)
	for i:= range nums {
		dic[nums[i]]++;
	}
    entrySet := []entry{}
	for k, v := range dic {
		entrySet = append(entrySet, entry{key: k, count: v})
	}
	sort.Slice(entrySet, func(i, j int) bool {
		return entrySet[i].count > entrySet[j].count;
    })
    
    res := []int{}
	for i:= 0; i<k; i++ {
        res = append(res, entrySet[i].key);
	}
	return res

}

type entry struct {
	key int;
	count int;
}