(function () {

	function appendStyles() {
		var css = document.createElement('style');
		css.type = 'text/css';
		css.id = 'hylyht_styles';
		
		var styles = '.hylyht_highlight { background-color: #ff9 }';
		styles += ' .hylyht_bookmarklet_menu { '+
				'position: fixed; '+
				'top: 0; '+
				'left: 0; '+
				'z-index: 2147483647; '+
				'clear: both; '+
				'background-color: black; '+
				'opacity: 0.7; '+
				'text-align: left; '+ 
				'height: 3em; '+
				'width: 100%; '+
				'border-bottom-color: black; '+
				'border-bottom-width: 1px; '+
				'border-bottom-style: solid; '+
			'}';
		styles += ' .hylyht_annotation { '+
				'float: left; '+
				'margin-left: 1em; '+
				'margin-top: 0.7em; '+
				'margin-bottom: 1em; '+
				'padding-left: 1em; '+
				'padding-right: 1em; '+
				'font-weight: normal; '+
				'color: lightgrey !important; ' +
			'}';
		styles += ' .hylyht_menu_button { '+
				'display: block; '+
				'float: left; '+
				'margin-left: 1em; '+
				'margin-top: 0.7em; '+
				'margin-bottom: 1em; '+
				'padding-left: 1em; '+
				'padding-right: 1em; '+
				'text-decoration: none; '+
				'font-weight: normal; '+
				'color: white !important; ' +
			'}';
		styles += ' .hylyht_menu_button:hover { '+
				'font-weight: bold; '+
				'font-style: italic; '+
			'}';
		
		if (css.styleSheet) css.styleSheet.cssText = styles;
		else css.appendChild(document.createTextNode(styles));
		
		document.head.appendChild(css);
	}
	
	function createAnnotation(text) {
		var span = document.createElement('span'); 
        span.className = 'hylyht_annotation';
        span.appendChild(document.createTextNode(text));
        return span;
	}
	
    function createButton(text, clickHandler) {
        var button = document.createElement('a'); 
        button.className = 'hylyht_menu_button';
        button.appendChild(document.createTextNode(text)); 
        
        var href = document.createAttribute('href');
        href.nodeValue = '#';
        button.setAttributeNode(href);
        
        if (button.addEventListener) 
            button.addEventListener("click", clickHandler, false);
        else 
            button.attachEvent('onclick', clickHandler);

        return button;
    }
    
	function getSelectionHtml() {
	    var html = "";
	    if (typeof window.getSelection != "undefined") {
	        var sel = window.getSelection();
	        if (sel.rangeCount) {
	            var container = document.createElement("div");
	            for (var i = 0, len = sel.rangeCount; i < len; ++i) {
	                container.appendChild(sel.getRangeAt(i).cloneContents());
	            }
	            html = container.innerHTML;
	        }
	    } else if (typeof document.selection != "undefined") {
	        if (document.selection.type == "Text") {
	            html = document.selection.createRange().htmlText;
	        }
	    }
	    return html;
	}
	
	function submitSelection() {
		alert(getSelectionHtml());
		// popup dialog to annotate highlight...
		// if a page already has highlights then the toolbar should mark the highlights and provide tooltips if the user 
		// hovers, containing previous annotations.
	}

    function cancel() {
    	
    	function removeClass(el, classValue) {
    		var classes = el.className.split(' ');
            var newClassName = '';
            for (i=0; i < classes.length; i++) {
            	if (classes[i] !== classValue) {
            		newClassName += classes[i] +' ';
            	}
            }
            el.className = newClassName;
    	}
    	
        var hylyhtMenu = document.getElementsByClassName('hylyht_bookmarklet_menu')[0];
        document.body.removeChild(hylyhtMenu);
        
        var highlights = document.getElementsByClassName('hylyht_highlight');
        for (i=0; i < highlights.length; i++) {
        	removeClass(highlights[i], 'hylyht_highlight');
        }
        
        var style = document.getElementById('hylyht_styles');
        document.head.removeChild(style);
        
        window.bookmarklet = undefined; 
    }

    // Change menu so that it displays down the right hand side of the page?? 
    // Could slide in and out from the right hand side along the top of the window??
	function showMenu() {
	    var hylyhtMenu = document.createElement('div');
	    hylyhtMenu.className = 'hylyht_bookmarklet_menu';
	    
	    document.body.insertBefore(hylyhtMenu, document.body.firstChild);
	    hylyhtMenu.appendChild(createAnnotation('Select text and submit as a highlight...'));
	    hylyhtMenu.appendChild(createButton('Submit Selection', submitSelection));
	    hylyhtMenu.appendChild(createButton('Cancel', cancel));
	}

    // add bookmarklet stylesheet
	appendStyles();
	showMenu();
})();
