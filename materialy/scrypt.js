/* Copyright Mariusz Śliwiński (http://math.edu.pl)*/

var val, cnt, err, F, mouse_right, sum;
var diffNum=0, levelNum, xx, yy;
var bx, by, ex, ey;

var T = new Array();
var P = new Array();
var S = new Array();
//--------------------------------------------------------

function rightButton(e) 
{ 
 if (e.button) return (e.button==2) ? true:false;
 return false;
}
//--------------------------------------------------------

function getEvent(e) {return(typeof e=='undefined')?window.event:e;}
function getTargetId(e){return e.target?e.target:e.srcElement;}
//--------------------------------------------------------

function getId(e)
{
  var target=getTargetId(getEvent(e));
  val = target.id;    
  mouse_right = rightButton(e);
}
//--------------------------------------------------------

function mouseDown(e)
{
   var i, j, char;
   getId(e);
   j = val%yy;
   i = (val-j)/yy;
    
   if (i>=0 && j>=0)
   {
    i++; j++;
    char = T[val];
    
    if (mouse_right==false)
    switch (char)
    {
     case '.': char='s'; break;
     case 's': char='.'; break;
     case 'x': char='.'; break;
     default: break;    
    }
    else
    switch (char)
    {
     case '.': char='x'; break;
     case 'x': char='.'; break;
     default: break;
    }
   
    T[val]=char;
    if (char!='x') P[i][j] = char;
      
    if (char == '.') document.getElementById(val).innerHTML = "";
    if (char == 's') document.getElementById(val).innerHTML = '<img style="margin-bottom:-3px;" id="'+val+'" src="images/skarby/s.gif" alt="" />';
    if (char == 's') document.getElementById(val).style.color = '#996600';
    if (char == 'x') document.getElementById(val).innerHTML = "x";
       
   document.getElementById("msg").innerHTML = '';
   Paint();
   Check();   
  }
}
//------------------------------------------------

function Paint()
{
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++)
    if (P[i][j]>=0)
     document.getElementById((i-1)*yy+j-1).style.color = "black";    
    else
     document.getElementById((i-1)*yy+j-1).style.color = "black";
}
//------------------------------------------------

function Check()
{
	var i, j, m, v;	   
   F = 1;
   //err = '<p>.</p>';

 		
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++)
    {
      if (P[i][j]>=0) 
      {
       sum=0;
       if (P[i-1][j]=='s') sum++;
       if (P[i+1][j]=='s') sum++;
       if (P[i][j-1]=='s') sum++;
       if (P[i][j+1]=='s') sum++;
       if (P[i-1][j-1]=='s') sum++;
       if (P[i-1][j+1]=='s') sum++;
       if (P[i+1][j-1]=='s') sum++;
       if (P[i+1][j+1]=='s') sum++;
       
       if (sum==P[i][j])
       {
        document.getElementById((i-1)*yy+j-1).style.color = "blue"; //#6666CC
       }
       else
       {
        F=0;
        if (sum>P[i][j])
         document.getElementById((i-1)*yy+j-1).style.color = "red";
       } 
      }
    }
        
   document.getElementById("msg").innerHTML = ''; 
   
   if (F==1) document.getElementById("msg").innerHTML = '<p><img src="images/odp.gif" alt="" /><br /> <span style="color: green">Brawo, łamigłówka rozwiązana</span></p>';
   
   //document.getElementById("err").innerHTML = err;  
	
}
//------------------------------------------------

function buildBoard() 
{

   var row, col, val, str = '';
   
	str += '<table cellspacing="0" cellpadding="0" style="cursor:default; border-color: black; border-style: solid; ">';
	
	for (row=0; row<xx; row++) 
	{
		str += '<tr>';
		for (col=0; col<yy; col++) 
		{
		   val = row*yy + col;
		   if (T[val] == '.')
			str += '<td style="width:35px; background-color: #EFEFEF; height:35px; color: black; border-color: black; border-style:ridge; text-align:center; border-width: 1px;" id="'+val+'"> </td>';
			else
			{
			str += '<td style="width:35px; background-color: #D4D4D4; height:35px; color: black; font-weight: bold; border-color: black; border-style:ridge; text-align:center; border-width: 1px;" id="'+val+'">'+T[val]+'</td>';
			 
			}
		}
		str += '</tr>';
	}
	
	str += '</table>'

	document.getElementById("board").innerHTML = str;	
	
   canvas = document.getElementById('board');
	canvas.oncontextmenu = function(){return false};	
	canvas.onmouseup = mouseDown;

}
//---------------------------------------------------------------

function dotS(i, j)
{
   sum=0;
   if (P[i-1][j]=='.' || P[i-1][j]=='*') sum++;
   if (P[i+1][j]=='.' || P[i+1][j]=='*') sum++;
   if (P[i][j-1]=='.' || P[i][j-1]=='*') sum++;
   if (P[i][j+1]=='.' || P[i][j+1]=='*') sum++;
   if (P[i-1][j-1]=='.' || P[i-1][j-1]=='*') sum++;
   if (P[i-1][j+1]=='.' || P[i-1][j+1]=='*') sum++;
   if (P[i+1][j-1]=='.' || P[i+1][j-1]=='*') sum++;
   if (P[i+1][j+1]=='.' || P[i+1][j+1]=='*') sum++;
       
return sum;
}
//---------------------------------------------------------------


function numberS(i, j)
{
   sum=0;
   if (P[i-1][j]=='*') sum++;
   if (P[i+1][j]=='*') sum++;
   if (P[i][j-1]=='*') sum++;
   if (P[i][j+1]=='*') sum++;
   if (P[i-1][j-1]=='*') sum++;
   if (P[i-1][j+1]=='*') sum++;
   if (P[i+1][j-1]=='*') sum++;
   if (P[i+1][j+1]=='*') sum++;
       
return sum;
}
//---------------------------------------------------------------

function cntr(i, j)
{
   sum=0;
   if (P[i-1][j]>0) sum++;
   if (P[i+1][j]>0) sum++;
   if (P[i][j-1]>0) sum++;
   if (P[i][j+1]>0) sum++;
   if (P[i-1][j-1]>0) sum++;
   if (P[i-1][j+1]>0) sum++;
   if (P[i+1][j-1]>0) sum++;
   if (P[i+1][j+1]>0) sum++;
       
return sum;
}
//---------------------------------------------------------------

function empty(i, j)
{
   if (P[i][j]=='.' || P[i][j]=='*' || P[i][j]=='b') return true;

return false;
}
//---------------------------------------------------------------


function generateBoard()
{
	var i, j, rand;	

	for (i=0; i<=xx+1; i++) 
    for (j=0; j<=yy+1; j++) 
    {
     if (i==0 || j==0 || j==yy+1  || i==xx+1) P[i][j] = 'b'; 
     else
      P[i][j] = '.'; 
    }
    
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++) 
    {
     rand = Math.floor(Math.random() * 101);  
     if (rand%4==0) P[i][j]='*';
    }

   for (i=1; i<xx; i++) 
    for (j=1; j<yy-1; j++) 
    {
     if (P[i][j]=='.' && P[i][j+1]=='.' && P[i][j+2]=='.' && P[i+1][j]=='.' && P[i+1][j+1]=='.' && P[i+1][j+2]=='.')
     {
      rand = Math.floor(Math.random() * (97)); 
      if (rand%6==1) P[i][j]='*';
      if (rand%6==2) P[i][j+1]='*';
      if (rand%6==3) P[i][j+2]='*';
      if (rand%6==4) P[i+1][j]='*';
      if (rand%6==5) P[i+1][j+1]='*';
      if (rand%6==0) P[i+1][j+2]='*';
     }
    }

   for (i=1; i<xx-1; i++) 
    for (j=1; j<yy; j++) 
    {
     if (P[i][j]=='.' && P[i][j+1]=='.' && P[i+1][j]=='.' && P[i+1][j+1]=='.' && P[i+2][j]=='.' && P[i+2][j]=='.')
     {
      rand = Math.floor(Math.random() * (87)); 
      if (rand%6==0) P[i][j]='*';
      if (rand%6==1) P[i][j+1]='*';
      if (rand%6==2) P[i+1][j]='*';
      if (rand%6==3) P[i+1][j+1]='*';
      if (rand%6==4) P[i+2][j]='*';
      if (rand%6==5) P[i+2][j+1]='*';
     }
    }
   
   for (i=1; i<=xx; i++) 
    for (j=1; j<yy-2; j++) 
     if (P[i][j]=='*' && P[i][j+1]=='*' && P[i][j+2]=='*' && P[i][j+3]=='*') P[i][j+2]='.';

   for (i=1; i<xx-2; i++) 
    for (j=1; j<=yy; j++) 
     if (P[i][j]=='*' && P[i+1][j]=='*' && P[i+2][j]=='*' && P[i+3][j]=='*') P[i+2][j]='.';

   for (i=1; i<xx; i++) 
    for (j=1; j<yy; j++) 
     if (P[i][j]=='*' && P[i+1][j]=='*' && P[i][j+1]=='*' && P[i+1][j+1]=='*') P[i][j+1]='.';         
		
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++)	
	 { 
	  rand = Math.floor(Math.random() * (101));
	  if (P[i][j]=='.' && rand%2==0) P[i][j] = numberS(i,j); 
	 }

	 
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++)	
	 { 
	  if (P[i][j]=='*')
	  {
	   if (cntr(i,j)==0)
	   {
	    rand = Math.floor(Math.random() * (121));
	    if (P[i-1][j-1]=='.' && rand%3==0) P[i-1][j-1] = numberS(i-1,j-1);
	    if (P[i-1][j]=='.' && rand%2==1) P[i-1][j] = numberS(i-1,j);
	    if (P[i-1][j+1]=='.' && rand%2==0) P[i-1][j+1] = numberS(i-1,j+1);
	    if (P[i+1][j-1]=='.' && rand%3==1) P[i+1][j-1] = numberS(i+1,j-1);
	    if (P[i+1][j]=='.' && rand%2==0) P[i+1][j] = numberS(i+1,j);
	    if (P[i+1][j+1]=='.' && rand%2==1) P[i+1][j+1] = numberS(i+1,j+1);
	    if (P[i][j-1]=='.' && rand%2==0) P[i][j-1] = numberS(i,j-1);
	    if (P[i][j+1]=='.' && rand%2==1) P[i][j+1] = numberS(i,j+1);
	   }
	   
	  } 
	 }	 

   for (i=1; i<xx; i++) 
    for (j=1; j<yy-1; j++) 
    {

     if (empty(i,j) && empty(i,j+1) && empty(i,j+2) && empty(i+1,j) && empty(i+1,j+1) && empty(i+1,j+2))
     {
      rand = Math.floor(Math.random() * (117)); 
      if (rand%3==0 && P[i][j]=='.') P[i][j]=numberS(i,j);
      if (rand%3==1 && P[i][j+1]=='.') P[i][j+1]=numberS(i,j+1);
      if (rand%3==2 && P[i][j+2]=='.') P[i][j+2]=numberS(i,j+2);
      if (rand%3==0 && P[i+1][j]=='.') P[i+1][j]=numberS(i+1,j);
      if (rand%3==1 && P[i+1][j+1]=='.') P[i+1][j+1]=numberS(i+1,j+1);
      if (rand%3==2 && P[i+1][j+2]=='.') P[i+1][j+2]=numberS(i+1,j+2);
 
     }
    }


   for (i=1; i<xx-1; i++) 
    for (j=1; j<yy; j++) 
    {

     if (empty(i,j) && empty(i,j+1) && empty(i+1,j) && empty(i+1,j+1) && empty(i+2,j) && empty(i+2,j+1))
     {
      rand = Math.floor(Math.random() * (101)); 
      if (rand%3==0 && P[i][j]=='.') P[i][j]=numberS(i,j);
      if (rand%3==1 && P[i][j+1]=='.') P[i][j+1]=numberS(i,j+1);
      if (rand%3==2 && P[i+1][j]=='.') P[i+1][j]=numberS(i+1,j);
      if (rand%3==0 && P[i+1][j+1]=='.') P[i+1][j+1]=numberS(i+1,j+1);
      if (rand%3==1 && P[i+2][j]=='.') P[i+2][j]=numberS(i+2,j);
      if (rand%3==2 && P[i+2][j+1]=='.') P[i+2][j+1]=numberS(i+2,j+1);
 
     }
    }

	 
   for (i=1; i<=xx; i++) 
    for (j=1; j<yy; j++) 
     if (P[i][j]==0 && P[i][j+1]==0) P[i][j+1]='.';
     
   for (i=1; i<xx; i++) 
    for (j=1; j<=yy; j++) 
     if (P[i][j]==0 && P[i+1][j]==0) P[i+1][j]='.'; 
   
   for (i=1; i<xx; i++) 
    for (j=1; j<yy; j++) 
     if (P[i][j]>=0 && P[i+1][j]>=0 && P[i][j+1]>=0 && P[i+1][j+1]>=0) P[i][j+1]='.';         

	//var str='';   
   	
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++) 
    {
     if (P[i][j]>0 && P[i][j]<7 && dotS(i,j)==P[i][j]) 
     {
     //str+= i+','+j+'----------->'+P[i][j]+'<br />'
     P[i][j]='.'; 
     }
    }
   
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++) 
    {   
     if (empty(i,j) && empty(i-1,j-1) && empty(i-1,j) && empty(i-1,j+1) && empty(i+1,j-1) && empty(i+1,j) && empty(i+1,j+1) && empty(i,j-1) && empty(i,j+1)) 
     {
      P[i][j] = numberS(i,j); 
      //str+= i+','+j+'->'+P[i][j]+'<br />'
     }
     
    }
   
   /*/   
   for (i=1; i<=xx; i++) 
   {
    for (j=1; j<=yy; j++) 
      str+= P[i][j]+' ';
   str+= '<br />';
   }
   //*/
   
   if (P[1][1]==0) P[1][1]='.';
   if (P[1][yy]==0) P[1][yy]='.';
   if (P[xx][1]==0) P[xx][1]='.';
   if (P[xx][yy]==0) P[xx][yy]='.';
   
   for (i=1; i<=xx; i++) 
    for (j=1; j<=yy; j++) 
     if (P[i][j]=='*') P[i][j]='.';
       
	//document.getElementById("err").innerHTML = str;   
}
//--------------------------------------------------------------------

function loadLevel() 
{	
	var i, j;	
	xx = diffNum; 
   yy = diffNum; 
	
	for (i=0; i<=xx+1; i++)
	{
    P[i] = new Array(yy+2);
    S[i] = new Array(yy+2); 
   }
   
	generateBoard();
	
	for (var x=0; x<xx*yy; x++)
	{
    j = x%yy;
    i = (x-j)/yy;

    T[x] = P[i+1][j+1];
    S[i+1][j+1]= P[i+1][j+1]
   }  


}
//-------------------------------------------------------------

function newGame() 
{
   document.getElementById("msg").innerHTML = '<p>.</p>';
	document.getElementById("err").innerHTML = '<p>.</p>';
   loadLevel();
	buildBoard();

}
//---------------------------------------------------------------

function startGame() 
{
	diffNum = document.skarby.diff.options[document.skarby.diff.selectedIndex].value;
	newGame();
}
//--------------------------------------------------------------

function resetGame() 
{
	var i, j;
	
	for (var x=0; x<xx*yy; x++)
	{
    j = x%yy;
    i = (x-j)/yy;

    T[x] = S[i+1][j+1];
    P[i+1][j+1] = S[i+1][j+1]
   }
   
   document.getElementById("msg").innerHTML = '<p>.</p>';
   buildBoard();
}
//--------------------------------------------------------------
