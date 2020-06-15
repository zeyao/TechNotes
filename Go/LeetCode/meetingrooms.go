package main

import "fmt"
import "sort"

func main() {
	arr := [][]int {
		{0,3},
		{2,3},
		{4,5},
	}
	fmt.Println(canAttendMeetings(arr));
}

func canAttendMeetings(intervals [][]int) bool {
    sort.Slice(intervals, func(i, j int) bool {
        return intervals[i][0] < intervals[j][0];
    })

    for i:= 1; i < len(intervals);i++ {
        if (intervals[i][0] < intervals[i-1][1]) {
            return false;
        }
    }
    return true;
}  