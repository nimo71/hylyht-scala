/*
    The bookmarklet adds a top menu bar to allow various highlighing on 
    the page and the submission of page content the user is interested in. 

    If the bookmarklet fails to run for any reason then submit the url to 
    the server for analysis. 

    ...
*/

(function () {
	function appendStyles() {
		var css = document.createElement('style');
		css.type = 'text/css';
		css.id = 'ocre_styles';
		
		var styles = '.ocre_highlight { background-color: #ff9 }';
		styles += ' .ocre_bookmarklet_menu { '+ 
				'position: absolute; '+
				'top: 0; '+
				'left: 0; '+
				'z-index: 2147483647; '+
				'clear: both; '+
				'background-color: grey; '+
				'opacity: 0.85; '+
				'text-align: left; '+ 
				'height: 3em; '+
				'width: 100%; '+
				'border-bottom-color: black; '+
				'border-bottom-width: 1px; '+
				'border-bottom-style: solid; '+
			'}';
		styles += ' .ocre_menu_button { '+
				'display: block; '+
				'float: left; '+
				'margin-left: 1em; '+
				'margin-top: 0.7em; '+
				'margin-bottom: 1em; '+
			'}';
		
		if (css.styleSheet) css.styleSheet.cssText = styles;
		else css.appendChild(document.createTextNode(styles));
		
		document.head.appendChild(css);
	}
	
	
    function highlightAllParagraphs() {
        var els = Array.prototype.slice.call(document.getElementsByTagName('p'))
            // .concat(Array.prototype.slice.call(document.getElementsByTagName('h1')))
            // .concat(Array.prototype.slice.call(document.getElementsByTagName('h2')))
            // .concat(Array.prototype.slice.call(document.getElementsByTagName('h3')))
            // .concat(Array.prototype.slice.call(document.getElementsByTagName('h4')))
            ;

        for (i=0; i < els.length; i++) {
            // change to add a style to elements, can then be removed on toggle
            els[i].className = 'ocre_highlight ' + els[i].className;
        }
    }

    function submit() { alert('Submitting...'); }

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
    	
        var ocreMenu = document.getElementsByClassName('ocre_bookmarklet_menu')[0];
        document.body.removeChild(ocreMenu);
        
        var highlights = document.getElementsByClassName('ocre_highlight');
        for (i=0; i < highlights.length; i++) {
        	removeClass(highlights[i], 'ocre_highlight');
        }
        
        var style = document.getElementById('ocre_styles');
        document.head.removeChild(style);
        
        window.bookmarklet = undefined; 
    }

    function createButton(text, clickHandler) {
        var button = document.createElement('a'); 
        button.className = 'ocre_menu_button';
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

    // add bookmarklet stylesheet
	appendStyles();

    // add menu to top of the document 
    var ocreMenu = document.createElement('div');
    ocreMenu.className = 'ocre_bookmarklet_menu';
    
    document.body.insertBefore(ocreMenu, document.body.firstChild);
    ocreMenu.appendChild(createButton('Submit', submit));
    ocreMenu.appendChild(createButton('All paragraphs', highlightAllParagraphs));
    ocreMenu.appendChild(createButton('Cancel', cancel));

    // // highlight user selection 
    // function selectElementContents(el) {
    //     var range;
    //     if (window.getSelection && document.createRange) {
    //         range = document.createRange();
    //         var sel = window.getSelection();
    //         range.selectNodeContents(el);
    //         sel.removeAllRanges();
    //         sel.addRange(range);
    //     } 
    //     else if (document.body && document.body.createTextRange) {
    //         range = document.body.createTextRange();
    //         range.moveToElementText(el);
    //         range.select();
    //     }
    // }

    // Longest paragraph...
    // var paragraphs = document.getElementsByTagName('p');
    // var longest = 0;
    // var longestIndex = undefined;
    // for (var i=0; i < paragraphs.length; i++) {
    //     for (var j=0; j < paragraphs[i].childNodes.length; j++) {
    //         var curNode = paragraphs[i].childNodes[j];
    //         if (curNode.nodeName === "#text") {
    //             var text = curNode.nodeValue;
    //             var paraLength = text.length; 
    //             if (paraLength > longest) {
    //                 longest = paraLength; 
    //                 longestIndex = i; 
    //             }        
    //         }
    //     }
    // }; 
    // paragraphs[longestIndex].style.background = "#ff9";

})();
