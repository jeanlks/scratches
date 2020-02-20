const arr = [1, 2, 3, 4, 5];

const sum = arr.reduceRight((total, item) => total + item, 0);
console.log(sum);