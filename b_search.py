
class SortAndSearch():
		
	def binary_search(self, k):
		low = 0
		high = len(numbers)
		while low <= high:
			mid = int((low + high)/2)
			if (numbers[mid] < k):
				low = mid + 1
			elif (numbers[mid] > k):
				high = mid - 1
			else:
				return mid
		return -1

	def quick_sort(self, A, i, k):
		if i < k:
			p = self.partition(A, i, k)
			self.quick_sort(A, i, p - 1)
			self.quick_sort(A, p+1, k)

	def partition(self, array, left, right):
		pivotIndex = int((left+right)/2)
		pivotValue = array[pivotIndex]
		array[pivotIndex], array[right] = array[right], array[pivotIndex]
		storeIndex = left
		for i in range(left, right):
			if array[i] < pivotValue:
				array[i], array[storeIndex] = array[storeIndex], array[i]
				storeIndex = storeIndex + 1
		array[storeIndex], array[right] = array[right], array[storeIndex]
		return storeIndex

def main():
	sortSearch = SortAndSearch()
	numbers = [10, 5, 1000, 150, 70, 30, 200, 60, 400, 1, 8, 90, 173, 42]	
	print("".join([str(x)+ " " for x in numbers]))
	sortSearch.quick_sort(numbers, 0, len(numbers)-1)
	print("".join([str(x) + " " for x in numbers]))
if __name__ == '__main__': main()
