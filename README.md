<h1>Camelot - 3D Story Game</h1>

 ### [YouTube Demonstration](https://www.youtube.com/watch?v=zbngxmm9Qk4)

<h2>Description</h2>
Camelot is a project that myself, and my 2 teammates developed over the course of the semester for our Intro to Computer Science II course. This project consists of a choice-based narrative adventure game with a medieval setting, inside a premade sandbox built in the game engine Unity. Your character is Doug Do Good and you navigate through many character interactions and locations, and make choices that lead you to the "good" ending. My team and I designed the story using Twine, consisting of over 50 "nodes", or points of choice in the story. Each node represents an action, dialogue choice, or location change, with corresponding edges/branches connecting that choice to another to forward the story. There are 6 endings, only 2 of which are good. 
<br />
- The nodes are stored in MyNodeBuilder
<br />
- The node labels are enums stored in MyNodeLabels
<br />
- The edges are stored in MyEdgeBuilder
<br />
- The edge labels are enums stored in MyChoiceLabels
<br />
- The entity objects are stored in MyStoryEnitites 
<br />
• Each node consists of a label which is referenced by the edges, and actions that are picked from a given library that trigger movement or show text to the player. 
<br />
• Each edge consists of the parent node that is referenced from MyNodeLabels, the child node or nodes that are also referenced from MyNodeLabels, and the player input that triggers the child node event. 
<br />
<h2>UML Diagram:</h2>

<img width="849" alt="Screenshot 2024-10-23 at 5 03 00 PM (4)" src="https://github.com/user-attachments/assets/67770f95-eb4f-4301-a875-3a27c4a9e7e3" />



<br />


<h2>Languages Used</h2>

- <b>Java</b> 

<h2>Environments Used </h2>

- <b>Eclipse</b>



