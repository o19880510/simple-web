/**
 * API
 * 仍然在开发中
 * 
 * 
 * 
 * 
 * config配置：
 * 
 * 1	Epg.config.log	日志记录配置相关
 * 
 * 
 * 
 * function功能：
 * 
 * 1	Epg.utils.getById(id)	根据id获取dom元素
 * 2	Epg.utils.isExist(id)	根据id判断dom元素是否存在
 * 3	Epg.utils.log(content)	记录日志
 * 
 * 4	Epg.Button.init(inButtonIds, focusId)	
 * 		初始化页面按钮布局。
 * 		inButtonIds页面所有可控id，二维数组
 * 		focusId 初始化聚焦dom的id
 * 5	Epg.Button.moveLeft()	元素向左聚焦
 * 6	Epg.Button.moveRight()	元素向右聚焦
 * 7	Epg.Button.moveTop()	元素向上聚焦
 * 8	Epg.Button.moveDown()	元素向下聚焦
 * 
 * 9	Epg.eventHandler(keyCode)	事件处理函数。keyCode按键编码
 * 
 * 
 * 
 * 
 */

(function(window){
	var Epg = window.Epg = {
		KEY_BACK : 0x0008, // 返回/删除
		KEY_ENTER : 0x000D, // 确定
		KEY_PAGE_UP : 0x0021, // 上页
		KEY_PAGE_DOWN : 0x0022, // 下页
		KEY_LEFT : 0x0025, // 左
		KEY_UP : 0x0026, // 上
		KEY_RIGHT : 0x0027, // 右
		KEY_DOWN : 0x0028, // 下
		KEY_0 : 0x0030, // 0       
		KEY_1 : 0x0031, // 1
		KEY_2 : 0x0032, // 2
		KEY_3 : 0x0033, // 3
		KEY_4 : 0x0034, // 4
		KEY_5 : 0x0035, // 5
		KEY_6 : 0x0036, // 6 
		KEY_7 : 0x0037, // 7
		KEY_8 : 0x0038, // 8
		KEY_9 : 0x0039, // 9
		KEY_VOL_UP : 0x0103, // Vol+，音量加
		KEY_VOL_DOWN : 0x0104, // Vol-，音量减
		KEY_MUTE : 0x0105, // Mute，静音
		KEY_TRACK : 0x0106, // Audio Track，切换音轨
		KEY_PLAY_PAUSE : 0x0107, // >||，播放，暂停
		KEY_FAST_FORWARD : 0x0108, // >> ，快进
		KEY_FAST_REWIND : 0x0109, // << ，快退
		KEY_IPTV_EVENT : 0x0300, // 虚拟事件按键
		KEY_RED : 0x0113, // 红色键
		KEY_GREEN : 0x0114, // 绿色键
		KEY_YELLOW : 0x0115, // 黄色键
		KEY_BLUE : 0x0116 // 蓝色键
	};
	
	for(var p in Epg){
		var value = Epg[p];
		if(value && typeof value === 'number' ){
			Epg[value] = value;
		}
	}
	
	Epg.config = {
		log : {
			on : true,  // log日志开关，true表示记录日志
			domId : "_log" // 日志内容在那个dom元素中显示，在此处配置dom ID
		}
	};
	
	/**
	 * 扩展对象属性和方法
	 * @param  {Object, Function} target      要扩展的对象
	 * @param  {Object} extendFuncs 新的属性和方法对象
	 * @param  {Boolean} override    若对象属性已经存在，是否覆盖．true覆盖
	 * @return {void}             
	 */
	Epg.extend = function(target, extendFuncs, override) {
		if ( (typeof target === "object" || typeof target === "function")
				&& typeof extendFuncs === "object" ) {
			
			for(var p in extendFuncs){
				if(extendFuncs[p]){
					if( !target[p] || (target[p] && override)){
						target[p] = extendFuncs[p];
					}
				}
			}
		}
	};
	
	Epg.utils = {
			/**
			根据ID获取DOM元素
			*/
			getById : function(id) {
				return document.getElementById(id);
			},
			
			/**
			 * 根据ID判断DOM元素是否存在
			 * @param id
			 * @returns {Boolean}
			 */
			isExist : function(id) {
				
				if(id == null || id == '' || id == undefined || id == 'undefined'){
					return false;
				}
				
				return this.getById(id) ? true : false;
			},
			
			/**
			 * 判断对象是不是数组
			 * @param  {[type]}  obj [description]
			 * @return {Boolean}     [description]
			 */
			isArray: function(obj){
				if(typeof obj === '[object Array]'){
					return true;
				}
				return false;
			},
			
			/**
			 * 判断对象是不是类似数组
			 * @param  {[type]}  obj [description]
			 * @return {Boolean}     [description]
			 */
			isArrayLike: function(obj){
				if(typeof obj != '[object Array]' && typeof obj != 'function' && obj.length){
					return true;
				}
				return false;
			},
			
			/**
			 * 判断对象是不是数字
			 * @param  {[type]}  obj [description]
			 * @return {Boolean}     [description]
			 */
			isNumber: function(obj){
				if(typeof obj === 'number'){
					return true;
				}
				return false;
			},

			/**
			 * 元素展示
			 * @param  {[type]} id [description]
			 * @return {[type]}    [description]
			 */
			show: function(id){
				var obj = this.getById(id);
				if(obj){
					obj.style.display = '';
				}
			},
			/**
			 * 元素隐藏
			 * @param  {[type]} id [description]
			 * @return {[type]}    [description]
			 */
			hide: function(id){
				var obj = this.getById(id);
				if(obj){
					obj.style.display = 'none';
				}
			},		
			// input: http://localhost:8083/uimcardprj/share/meun.jsp
			// output: http://localhost:8083/uimcardprj/
			/**
			 * 获取项目路径
			 * 例：input: http://localhost:8083/uimcardprj/share/meun.jsp
			 *	　　output: http://localhost:8083/uimcardprj/
			 * @return {[type]} [description]
			 */
			getProjectPath : function() {
				var href = window.location.href;
				var pathName = window.location.pathname;
	
				var localhostPaht = href.substring(0, href.indexOf(pathName));
				var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
				
				return localhostPaht + projectName + "/";
			},
			
			gotoIPTVPortal: function(){
				window.location.href = Authentication.CTCGetConfig('EPGDomain');
			},
			
			/**
			 * 日志记录
			 * 
			 * @param content
			 */
			log : function(content){
				
				if(Epg.config.log.on){
					
					if(console){
						console.log(content);
					}
					
					var dom = this.getById(Epg.config.log.domId);
					if(dom){
						if(dom.innerHTML){
							dom.innerHTML += content + "<br>";
						}
					}
				}
			}
	};
	
	Epg.validator = {
		_mobileReg: [/^13\d{9}$/, /^15[0-35-9]\d{8}$/, /^18[0235-9]\d{8}$/, /^(([0\+]\d{2,3})?(0\d{2,3}))?(\d{8})(-(\d{3,}))?$/],
		/**
		 * [isTeleMobile description]
		 * @param  {[type]}  value [description]
		 * @return {Boolean}       [description]
		 */
		isTeleMobile: function(value){
			for(var i in _mobileReg){
				var reg = this._mobileReg[i];
				if(reg.test(value)){
					return true;
				}
			}
			return false;
		}
	}
	
	
	(function ___(window){
		/**
		 * document.images = {
		 * 		// dom id 
				"water" : {
					src:"",
					oldSrc:""
				},
				// dom id 
				"ele": {
					src:"",
					oldSrc:""
				}, 
				current: "water" //当前选中id
		};
		*/
		var imagesStore = {};
		
		function Image(src, oldSrc){
			this.src = src;
			this.oldSrc = oldSrc;
		}
		
		function imageRestore(){
			var id = imagesStore.current;
			if(id && imagesStore[id]){
				document.getElementById(id).src = imagesStore[id].oldSrc;
			}
		}
		
		function imageChange(id, src){
			if(!imagesStore[id]){
				_newImage(id, src);
			}
			document.getElementById(id).src = src;
			imagesStore.current = id;
		}
		
		function _newImage(id, src){
			var oldSrc = document.getElementById(id).src;
			imagesStore[id] = new Image(src, oldSrc);
		}
		
		window.imageRestore = imageRestore;
		window.imageChange = imageChange;
		
	})(window);
	
	/**
	 * [Button description]
	 * @param {[type]} inButtonIds [description]
	 * @param {[type]} focusId     [description]
	 */
	Epg.Button = function(inButtonIds, focusId){
		
		function _Button(inButtonIds, focusId){
			
			this._freeze = false;
			this.buttonIds = inButtonIds, 
			this.rowPosition = -1, // 行数
			this.colPosition = 0, // 列数
			this.MAX_ROWS = buttonIds.length, // 最大行数
			this.MAX_COLS = buttonIds[0].length; // 最大列数
			
			this._checkButtonArrays();
			this._focusId(focusId);
			
		}
		
		_Button.prototype = {
			/**
			 * 检查id二维数组。将不存的id设置为null
			 */
			_checkButtonArrays: function(){
				for(var i = 0; i < this.MAX_ROWS; i++){
					for(var j = 0; j < this.MAX_COLS; j++){
						var id = this.buttonIds[i][j];
						var exist = Epg.utils.isExist(id);
						
						if(!exist){
							this.buttonIds[i][j] = null;
						}
					}
				}
			},
			
			_focusId: function(focusId){
				var checked = false;
				for(var i = 0; i < this.MAX_ROWS; i++){
					for(var j = 0; j < this.MAX_COLS; j++){
						var id = this.buttonIds[i][j];
						
						// 根据focusId聚焦元素
						// 若找不到匹配的id，则聚焦第一个元素
						if( (!checked && id) || ( focusId && id === focusId )){
							Epg.utils.getById(id).focus();
							checked = true;
							this.rowPosition = i;
							this.colPosition = j;
						}
					}
				}
			},
				
			moveLeft : function() {
				if(this._freeze || this.colPosition == 0){
					return ;
				}
				
				var rowArray = this._genJumpArr(this.rowPosition, this.MAX_ROWS -1), 
					colArray = this._genNormalArr(this.colPosition - 1, 0);
				
				var position = this._findPosition(rowArray, colArray, true);
				this._positionFocus(position);
			},
			
			moveRight : function() {
				if(this._freeze || this.colPosition == (this.MAX_COLS -1)){
					return ;
				}
				
				var rowArray = this._genJumpArr(this.rowPosition, this.MAX_ROWS -1),
					colArray = this._genNormalArr(this.colPosition + 1, this.MAX_COLS - 1);
				
				var position = this._findPosition(rowArray, colArray, true);
				this._positionFocus(position);
			},
			
			moveUp : function() {
				
				// 处于最上方
				if(this._freeze || this.rowPosition == 0){
					return ;
				}
				
				var rowArray = this._genNormalArr(this.rowPosition - 1, 0), 
					colArray = this._genJumpArr(this.colPosition, this.MAX_COLS -1);
				
				var position = this._findPosition(rowArray, colArray);
				this._positionFocus(position);
				
			},
			
			moveDown : function() {
				if(this._freeze || this.rowPosition == (this.MAX_ROWS -1)){
					return ;
				}
				
				var rowArray = this._genNormalArr(this.rowPosition + 1, this.MAX_ROWS - 1), 
				colArray = this._genJumpArr(this.colPosition, this.MAX_COLS -1);
				
				var position = this._findPosition(rowArray, colArray);
				this._positionFocus(position);
			},
			/**
			 *　button对象冻结
			 *冻结之后，将对事件不处理
			 * @return {[type]} [description]
			 */
			freeze: function(){
				this._freeze = true;
			},
			/**
			 * button对象解冻
			 * @return {[type]} [description]
			 */
			unfreeze: function(){
				this._freeze = false;
			},
			
			
			_positionFocus: function(position){
				if(position){
					console.log(position + position.row + position.col);
					this.rowPosition = position.row;
					this.colPosition = position.col;
					this._focus(position.row, position.col);
				}
			},
			_genNormalArr: function(start, end){
				var large = (end - start) > 0;
				var arr = [];
				if(large){
					while(start <= end){
						arr.push(start++);
					}	
				}else{
					while(start >= end){
						arr.push(start--);
					}
				}
				return arr;
			},
			_genJumpArr: function(start, end){
				var curArray = [start];
				for(var cur = start, i = 1 ; cur >= 0 && cur <= end; ++i){
					
					var _mycur = cur;
					
					if(i%2 == 1){
						_mycur = cur - i;
						if(_mycur < 0){
							while(++cur <= end){ curArray.push(cur); }
							break;
						}
					}else{
						_mycur = cur + i;
						if(_mycur > end){
							while(--cur >= 0){ curArray.push(cur); }
							break;
						}
					}
					cur = _mycur;
					curArray.push(cur);
				}
				
				return curArray;
			},
			
			_focus : function (x,y){
				var id = this.buttonIds[x][y];
				var obj = Epg.utils.getById(id);
				if(obj){
					obj.focus();
				}
			},
			
			isExist : function(x,y){
				var id = this.buttonIds[x][y];
				if(id){
					return Epg.utils.isExist(id);
				}
				return false;
			},
			
			/**
			 * 获取当前聚焦元素的ID
			 * @return {[type]} [description]
			 */
			getFocusId: function(){
				var id = this.buttonIds[this.rowPosition][this.colPosition];
				return id;
			},
			
			_findPosition : function(rows, cols, x){ // x 表示横向查询
				var checkId = function(row, col){
					var _id = this.buttonIds[row][col];
					if(_id){
						return {row : row, col : col};
					}
				}
				var row, col, position;
				
				if(!x){
					var temp = rows;
					rows = cols;
					cols = temp;
				}
				
				for(var i in rows ){
					for(var j in cols){
						row = rows[i];
						col = cols[j];
						position = x ? checkId(row, col) : checkId(col, row);
						if(position){
							return position;
						}
					}
				}
			},
			
			/**
			 * 事件处理函数
			 * @return {[type]} [description]
			 */
			eventHander: function(){
				var that = this;
				return function(keyCode, target, e){
					
					if(that._freeze) return;
					
					switch(keyCode){
						case Epg.KEY_LEFT:{
							that.moveLeft();
							return false;
						}
						break;
						
						case Epg.KEY_RIGHT:{
							that.moveRight();
							return false;
						}
						break;
						
						case Epg.KEY_UP:{
							that.moveUp();
							return false;
						}
						break;
						
						case Epg.KEY_DOWN:{
							that.moveDown();
							return false;
						}
						break;
					}
				};
			}
		}
		
		return new _Button(inButtonIds, focusId);
	}
	
	
	Epg.event = {
		
		keyCode: function(e){
			e = e || window.event;
			return e.which || e.keyCode;
		},
		
		target: function(e){
			return event.target || event.srcElement;
		},
		
		addListener : function(dom, eventName, eventHandler) {
			var that = this;
			function __(e){
				eventHandler.call(e, that.keyCode(e), that.target(e));
			};
			
			if (dom && dom.addEventListener) {
				dom.addEventListener(eventName, __, false);
			} else if (dom && dom.attachEvent) {
				dom.attachEvent("on" + eventName, __);
			}
		}
	};
	
	
	window.onload = function(){
		Epg.event.init();
	};
	
})(window);