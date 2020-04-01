import java.util.*;
import java.util.function.BiFunction;

class Program {
	static class ContinuousMedianHandler {
		static Heap lowers = new Heap(Program::MAX_HEAP_FUNCTION, new ArrayList<>());
		static Heap greaters = new Heap(Program::MIN_HEAP_FUNCTION, new ArrayList<>());

		double median = 0;

		public void insert(int number) {
			if (lowers.length == 0 || number < lowers.peek()) {
				lowers.insert(number);
			} else {
				greaters.insert(number);
			}
			rebalanceHeaps();
			updateMedian();
		}

		public double getMedian() {
			return median;
		}

		public void updateMedian() {
			if (lowers.length == greaters.length) {
				median = ((double) lowers.peek() + (double) greaters.peek()) / 2;
			} else if (lowers.length > greaters.length) {
				median = lowers.peek();
			} else {
				median = greaters.peek();
			}
		}

		public void rebalanceHeaps() {
			if (lowers.length - greaters.length == 2) {
				greaters.insert(lowers.remove());
			} else if (greaters.length - lowers.length == 2) {
				lowers.insert(greaters.remove());
			}
		}
	}

	public static class Heap {
		public BiFunction<Integer, Integer, Boolean> comparisonFunc;
		List<Integer> heap = new ArrayList<>();
		int length = 0;

		public Heap(BiFunction<Integer, Integer, Boolean> comparisonFunc, List<Integer> heap) {
			this.comparisonFunc = comparisonFunc;
		}

		public void insert(int number) {
			heap.add(number);
			length++;
			siftUp(heap.size() - 1, heap);
		}

		public int remove() {
			swap(0, heap.size() - 1, heap);
			int valueRemoved = heap.get(heap.size() - 1);
			heap.remove(heap.size() - 1);
			length--;
			siftDown(0, heap.size() - 1, heap);
			return valueRemoved;
		}

		public void siftUp(Integer idx, List<Integer> heap) {
			int parentIdx = (idx - 1) / 2;
			while (idx > 0) {
				if (comparisonFunc.apply(heap.get(idx), heap.get(parentIdx))) {
					swap(idx, parentIdx, heap);
					idx = parentIdx;
					parentIdx = (idx - 1) / 2;
				} else {
					return;
				}
			}
		}

		public void siftDown(int idx, int endIdx, List<Integer> heap) {
			int childOneIdx = idx * 2 + 1;
			while (childOneIdx <= endIdx) {
				int childTwoIdx = idx * 2 + 2 <= endIdx ? idx * 2 + 2 : -1;
				int idxToSwap;
				if (childTwoIdx != -1) {
					if (comparisonFunc.apply(heap.get(childTwoIdx), heap.get(childOneIdx))) {
						idxToSwap = childTwoIdx;
					} else {
						idxToSwap = childOneIdx;
					}
				} else {
					idxToSwap = childOneIdx;
				}

				if (comparisonFunc.apply(heap.get(idxToSwap), heap.get(idx))) {
					swap(idxToSwap, idx, heap);
					idx = idxToSwap;
					childOneIdx = idx * 2 + 1;
				} else {
					return;
				}

			}
		}

		public int peek() {
			return heap.get(0);
		}

		public void swap(int i, int j, List<Integer> heap) {
			int temp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, temp);
		}
	}

	public static Boolean MAX_HEAP_FUNCTION(Integer a, Integer b) {
		return a > b;
	}

	public static Boolean MIN_HEAP_FUNCTION(Integer a, Integer b) {
		return a < b;
	}

	public static void main(String[] args) {
		// Program.ContinuousMedianHandler medianHandler = new Program.ContinuousMedianHandler();
		// medianHandler.insert(10);
		// medianHandler.insert(5);
		// medianHandler.insert(10);
		// medianHandler.insert(100);
		// System.out.println(medianHandler.getMedian());
		System.out.println(Program.singleNumber(new int[]{4,1,2,1,2}));
		
	}
}
