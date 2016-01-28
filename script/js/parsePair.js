var t = function(){
	parsePair({"desc":"小屋","value":1,"growth":0,"key":0});
}

var Pair = function(title,key,val,growth,desc){
	this.title = title;
	this.key = key;
	this.val = val;
	this.growth = growth;
	this.desc = desc;
}

var parsePair = function(jsonPair){
	title = jsonPair.title;
	key = jsonPair.key;
	val = jsonPair.value;
	growth = jsonPair.growth;
	desc = jsonPair.desc;
	var p = new Pair(title,key,val,growth,desc);
	console.log(p)
	return p;
}

var parsePairs = function(text){
		return JSON.parse(text);
}

var jsonToNode = function(jsonPair,ele){
	children = ele.children;
	for(i=0;i<children.length;i++){
		child = children[i];
		if(child.getAttribute('ele')==='title'){
			child.value = jsonPair.title;
		}else if(child.getAttribute('ele')==='key'){
			child.value = jsonPair.key;
		}else if(child.getAttribute('ele')==='value'){
			child.value = jsonPair.value;
		}else if(child.getAttribute('ele')==='growth'){
			child.value = jsonPair.growth;
		}else if(child.getAttribute('ele')==='desc'){
			child.value = jsonPair.desc;
		}
	}
	console.log("jsonToNode:[jsonPair="+jsonPair+"][ele="+ele+"]");
}

var nodeToJson = function(ele){
	jsonPair = {};
	children = ele.children;
	for(i=0;i<children.length;i++){
		child = children[i];
		if(child.getAttribute('ele')==='title'){
			jsonPair.title = child.value;
		}else if(child.getAttribute('ele')==='key'){
			jsonPair.key = child.value;
		}else if(child.getAttribute('ele')==='value'){
			jsonPair.value = child.value;
		}else if(child.getAttribute('ele')==='growth'){
			jsonPair.growth = child.value;
		}else if(child.getAttribute('ele')==='desc'){
			jsonPair.desc = child.value;
		}
	}
	return jsonPair;
}

var jsonarrayToString = function(ja){
	console.log(ja)
	var str = "[\n";
	if(ja.length>0){
		str += "    "+ja[0];
	}
	for(var i=1;i<ja.length;i++){
		str += ",\n    "+ja[i];
	}
	str+="\n]";
	return str;
}

var addJsonToArray = function(array,jsonPair){
	array.push(JSON.stringify(jsonPair));
}

var isEmptyNode = function(ele){
	console.log(ele);
	children = ele.children;
	for(var i=0;i<children.length;i++){
		if(!isGood(children[i].value)){
			return true
		}
	}
	return false;
}

var isGood = function(text){
	return text?text.length>0:false;
}