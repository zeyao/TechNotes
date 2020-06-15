package main

import "fmt"
import "sort"

func main() {
	
}

func reverseVowels(s string) string {
	arr := []byte(s);
    left:= 0;
    right := len(s)-1;
	for left < right {
		for left < right && !isVowel(arr[left]) {
			left++;
		}
		for left < right && !isVowel(arr[right]) {
			right--;
		}
		if (left < right) {
			arr[left], arr[right] = arr[right], arr[left];
			left++;
			right--; 
		}
	}
	return string(arr);
}  

func isVowel(c byte) bool {
	if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
        return true
	}
	return false;
}