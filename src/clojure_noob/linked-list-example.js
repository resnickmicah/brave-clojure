var node3 = {
  value: "last",
  next: null
};
var node2 = {
  value: "middle",
  next: node3
};

var node1 = {
  value: "first",
  next: node2
};

var first = function(node) {
  return node.value;
};

var rest = function(node) {
  return node.next;
};

var cons = function(newValue, node) {
  return {
    value: newValue,
    next: node
  };
};

first(node1);
first(rest(node1));
first(rest(rest(node1)));
var node0 = cons("new first", node1);
first(node0);
first(rest(node0));

var map = function (list, transform) {
  if (list === null) {
    return null;
  } else {
    return cons(transform(first(list)), map(rest(list), transform));
  }
}

first(
  map(node1, function (val) { return val + " mapped!"})
);

// Redefine first, rest, and cons in terms of JS array functions
// Above map definition still works, doesn't care about underlying impl
first = function (array) {
  return array[0];
}

rest = function (array) {
  var sliced = array.slice(1, array.length);
  if (sliced.length == 0) {
    return null;
  } else {
    return sliced;
  }
}

cons = function (newValue, array) {
  return [newValue].concat(array);
}


var list = ["Transylvania", "Forks, WA"];
map(list, function (val) { return val + " mapped!"})
