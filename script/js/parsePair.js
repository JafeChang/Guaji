var Pair = function(type,title,key,val,growth,desc){
	this.type = type;
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
	type = jsonPair.type;
	var p = new Pair(type,title,key,val,growth,desc);
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
		if(child.getAttribute('ele')==='type'){
			child.value = jsonPair.type;
		}else if(child.getAttribute('ele')==='title'){
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
		if(child.getAttribute('ele')==='type'){
			jsonPair.type = child.value;
		}else if(child.getAttribute('ele')==='title'){
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
		if(!isGood(children[i].value)&&children[i].type!='checkbox'){
			return true
		}
	}
	return false;
}

var isGood = function(text){
	return text?text.length>0:false;
}

var addPrice = function(jsonPrice, type, key, value){
	
}

var getPrice = function(bt){
	var ele = bt.parentNode;
	console.log(ele);
	var json = nodeToJson(ele);
	jsonPairs = parsePairs(f("jsonsrc").value);
	for(var i=0;i<jsonPairs.length;i++){
		var jsonPair = jsonPairs[i];
		if(jsonPair.type===json.type&&json.key===jsonPair.key){
			console.log(jsonPair);
			return jsonPair.price;
		} 
	}
	return null;
}

var getProductions = function(bt){
	var ele = bt.parentNode;
	console.log(ele);
	var json = nodeToJson(ele);
	jsonPairs = parsePairs(f("jsonsrc").value);
	for(var i=0;i<jsonPairs.length;i++){
		var jsonPair = jsonPairs[i];
		if(jsonPair.type===json.type&&json.key===jsonPair.key){
			console.log(jsonPair);
			return jsonPair.productions;
		} 
	}
	return null;
}

