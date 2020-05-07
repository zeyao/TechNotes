package heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
	
	List<Integer> list = new ArrayList<>();
	// parent = nodeIndex - 1 / 2;
	// left = parentIndex * 2 + 1;
	// right = parentIndex * 2 + 2;
	
	public void offer(int num) {
		//add at last and shift up
		list.add(num);
		int index = list.size()-1;
		if (list.size() > 1) {
			shifUp(index, num);
		}	
	}
	
	private void shifUp(int index, int num) {
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			int parentNum = list.get(parentIndex);
			if (parentNum <= num) {
				break;
			}
			list.set(index,parentNum);
			index = parentIndex;			
		}
		list.set(index, num);
	}
	
	public Integer peek() {
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	public Integer poll() {
		if (list.isEmpty()) {
			return null;
		}
		int min = list.get(0);
		int lastNum = list.get(list.size()- 1);
		list.set(0, lastNum);
		list.remove(list.size()-1);
		if (!list.isEmpty()) {
			shifDown(0, list.get(0));
		}
		return min;
	}
	
	private void shifDown(int index, int num) {
		//将x逐层向下与当前点的左右孩子中较小的那个交换，直到x小于或等于左右孩子中的任何一个为止
		while (index < list.size()) {
			int leftIndex = index * 2 + 1;
			int rightIndex = index * 2 + 2;
			Integer leftChild = leftIndex < list.size() ? list.get(leftIndex) : null;
			Integer rightChild = rightIndex < list.size() ? list.get(rightIndex) : null;
		
			boolean hasSmaller = false;
			if (leftChild != null && leftChild < num) {
				hasSmaller = true;
			}
			if (rightChild != null && rightChild < num) {
				hasSmaller = true;
			}
			if (!hasSmaller) {
				break;
			}
			
			if (leftChild != null && (rightChild == null || leftChild < rightChild)) {
				list.set(index, leftChild);
				index = leftIndex;
			}
			else if (rightChild != null && (leftChild == null || rightChild < leftChild)) {
				list.set(index, rightIndex);
				index = rightIndex;
			}
		}
		list.set(index, num);
	}
	
}
