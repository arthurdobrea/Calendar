// Your existing code unmodified...
var iDiv = document.createElement('div');
iDiv.id = 'createEvent';
iDiv.className = 'createEvent';
document.getElementsByTagName('body')[0].appendChild(iDiv);

// Now create and append to iDiv
var innerDiv = document.createElement('div');
innerDiv.className = 'createEvent-2';

// The variable iDiv is still good... Just append to it.
iDiv.appendChild(innerDiv);