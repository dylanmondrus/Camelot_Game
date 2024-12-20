
package myclassproject.mystorygraph;

import static myclassproject.mystorygraph.MyStoryEntities.*;
import java.util.List;
import com.actions.*;
import com.sequences.*;
import com.storygraph.*;
import myclassproject.mystorygraph.MyNodeLabels;

public class MyNodeBuilder extends NodeBuilder {
    public MyNodeBuilder(List list) {
        super(list);
    }

    /**
     * Write a method for each node.
     * Use get to get a reference to the node using its label.
     * The method adds Camelot actions that execute in order when visiting that node.
     * These methods must have a BuilderMethod annotation.
     */
    @BuilderMethod
    // Jackson
    public void rootActions() {//DYLAN WORK START
        var root = get(MyNodeLabels.root.toString());
        root.add(new CreateAll(List.of(city, dungeon, tavern, forestpath, apple, sword, littorch, bottle, coin, hammer, bag)))
            .add(new CreateCharacterSequence(doug))
            .add(new CreateCharacterSequence(merchantbill))
            .add(new CreateCharacterSequence(bandit))
            .add(new CreateCharacterSequence(beggar))
            .add(new CreateCharacterSequence(drunkard))
            .add(new CreateCharacterSequence(bartender))
            .add(new CreateCharacterSequence(king))
            .add(new SetPosition(doug, city))
            .add(new SetPosition(bandit, forestpath, "DirtPile"))
            .add(new SetPosition(merchantbill, city, "Plant"))
            .add(new SetPosition(beggar, city, "Fountain"))
            .add(new SetPosition(drunkard, tavern, "BackRightStool"))
            .add(new SetPosition(bartender, tavern, "Bar"))
            .add(new SetPosition(bottle, tavern, "Table"))
            .add(new SetPosition(apple, city, "Barrel"))
            .add(new SetPosition(littorch, tavern, "RoundTable"))
            .add(new Face(doug, merchantbill))
            .add(new SetCameraFocus(doug))
            .add(new PlaySound("Kingdom"))
            .add(new SetTitle("The Adventures of Doug Do Good"))
            .add(new ShowMenu());
    }

    @BuilderMethod
    // Narration sequence // 
    public void StartGame() {
        var node = get(MyNodeLabels.StartGame.toString());
        node.add(new HideMenu())
        .add(new NarrationSequence("This is an open world game where you can visit multiple locations, interact with people, and accept quests."
        		+ " Your character is Doug Do Good. He has lived all his life as a peasant on a farm but has decided to leave his home in search for more adventure. "
        		+ "Inside the city square there are many places you can go and people you can meet."));
            
    }
    
    @BuilderMethod
    public void MenuShowCredits() { //Dylan
    	var node = get(MyNodeLabels.MenuShowCredits.toString());
    	node.add(new CreditsSequence("Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch. Course: CMPS 1600"));
    }
    @BuilderMethod
    public void MenuCloseCredits() { //Dylan
    	var node = get(MyNodeLabels.MenuCloseCredits.toString());
    	node.add(new HideCredits())
    	.add(new ShowMenu());
    }
    

    private Edge HideMenu() {
        // TODO Auto-generated method stub
        return null;
    }

    @BuilderMethod
    // 
    public void atCity() {
        var node = get(MyNodeLabels.atCity.toString());
        node.add(new HideNarration())
        .add(new EnableInput());
        // .add(new MenuChoice(MenuChoice.Options))
    }
    @BuilderMethod // 
    public void merchantbillTalkActions() { //Dylan
    	var node = get(MyNodeLabels.merchantbillTalk.toString());
    	node.add(new HideNarration()).add(new EnableInput())
    	.add(new DialogSequence(merchantbill, doug, List.of("Greetings I am Merchant Bill. I have no goods to sell you because I was vicously beaten and robbed by bandits. They stole my precious and valuable bag filled with items. I would do anything to get it back but I am just an old man who can not confront the bandits please help me. Would you be so kind to help me. You will be handsomely rewarded."),List.of("Accept Quest", "Decline Quest")));
    }
    @BuilderMethod //
    public void acceptActions() { //Dylan
    	var node = get(MyNodeLabels.acceptActions.toString());
    	node.add(new HideDialog()).add(new WalkTo(doug, cityExit)).add(new EnableInput());
    	
    }
    @BuilderMethod //
    public void atForestPathActions() { //Dylan
    	var node = get(MyNodeLabels.atForestPath.toString());
    	node.add(new SetPosition(doug, forestexit))
    	.add(new FadeIn())
    	.add(new NarrationSequence("In the forest path you notice a well a Bandit, a plant."))
    	.add(new Wait(3))
    	.add(new HideNarration())
    	.add(new EnableInput());
    }


    @BuilderMethod // 
    public void ConfrontBanditActions() { //Dylan
    	var node = get(MyNodeLabels.ConfrontBandit.toString());
    	node.add(new HideNarration()).add(new EnableInput()).add(new NarrationSequence("When you confront the bandit and ask for the merchants stolen cargo he threatens you with a hammer."))
    	.add(new Wait(3))
    	.add(new HideNarration())
    	.add(new Draw(bandit,hammer))
    	.add(new DialogSequence(bandit, doug, List.of("Are you willing to get your brains smashed out for this cargo?"), List.of("No I am not")));
    }
    	
    @BuilderMethod //  
    public void ForestPlantActions() { //Dylan
    	var node = get(MyNodeLabels.ForestPlant.toString());
    	node.add(new HideDialog()).add(new WalkTo(doug, forestplant)).add(new Kneel(doug)).add(new NarrationSequence("You are amazed by the plant and get distracted for a moment "
    	+ "but you refocus on the mission at hand.")).add(new Wait(4)).add(new HideNarration())
    	.add(new WalkTo(doug, well));
    	
    }
    @BuilderMethod //
    public void wellActions() { //Dylan
    	var node = get(MyNodeLabels.well.toString());
    	node.add(new HideNarration()).add(new NarrationSequence("While at the well you discover a sword at the bottom of it."
    			+ " Pick up the sword, and confront the bandit.")).add(new Wait(5)).add(new HideNarration()).add(new EnableInput()).add(new Pickup(doug, sword))
    	.add(new WalkTo(doug,bandit));
    }
    @BuilderMethod  //
    public void BanditSwordActions() { //Dylan
    	var node = get(MyNodeLabels.BanditSword.toString());
    	node.add(new HideNarration()).add(new DialogSequence(doug, bandit, List.of("I have a sword and I am not afraid to use it. Are you really willing to die for this bag?"),List.of("Kill Bandit", "Retreat")));
    	
    }
    @BuilderMethod //
    public void KillBanditActions() { //Dylan
    	var node = get(MyNodeLabels.KillBandit.toString());
    	node.add(new HideDialog())
    	.add(new Draw(doug, sword))
    	.add(new Attack(doug, bandit))
    	.add(new Die(bandit))
    	.add(new NarrationSequence("You swiftly murdered the bandit with your sword, and take the bag, but you have broken your morals you are no longer a good man."))
    	.add(new Wait(6))
    	.add(new HideNarration())
    	.add(new Pocket(doug, sword))
        .add(new SetPosition(bag, forestpath, "DirtPile"))
    	.add(new Pickup(doug, bag))
    	.add(new NarrationSequence("You return to the merchant to return his bag."))
    	.add(new Wait(3))
    	.add(new HideNarration())
    	.add(new WalkTo(doug, forestexit))
    	.add(new EnableInput());
    }
    @BuilderMethod //
    public void RetreatActions() { //Dylan
    var node = get(MyNodeLabels.Retreat.toString());
    node.add(new HideDialog()).add(new NarrationSequence("As Doug Do Good you have sworn to never harm anybody and you will not fold your morals for money. "
    + "Although you may not have gotten the poor old Merchant Bill's goods you still have your pride and morals."))
    .add(new Wait(10))
    .add(new HideNarration())
    .add(new WalkTo(doug, forestexit))
    .add(new EnableInput());
    }

    @BuilderMethod //
    public void MerchantRetreatActions() { //Dylan
    	var node = get(MyNodeLabels.MerchantRetreat.toString());
    	node.add(new SetPosition(doug, cityExit))
    	.add(new WalkTo(doug, merchantbill))
    	.add(new DialogSequence(doug, merchantbill, List.of("I have failed to retrieve your cargo because I did not want to kill the bandit."), List.of(" "))
    	.add(new Wait(3))
    	.add(new HideDialog())
    	.add(new SetPosition(merchantbill))
    	.add(new SetPosition(king, city, "Plant"))
    	.add(new Face(king, doug))
    	.add(new DialogSequence(king, doug, List.of("It is ok Doug. I am the king and I have no heirs to my throne."
    	+ " I posed as a merchant in need to see who has a kind enough heart to rule the city. "
    	+ "I appoint you Doug Do Good as the heir to my throne lead well my friend. "
    	+ "A good king can not fold on their morals and I see that you will not"), List.of("Become king", "Decline offer"))));
    }
    @BuilderMethod//  
    public void AcceptKingActions() { //Dylan
    	var node = get(MyNodeLabels.AcceptKing.toString());
    	node.add(new HideDialog())
    	.add(new NarrationSequence("You become king of the Kingdom of Camelot and are remembered as a benevolent and passive ruler. The End."))
    	.add(new Wait(6))
    	.add(new HideNarration())
    	.add(new CreditsSequence("Credits: Dylan Mondrus, Jackson Burch, Joshua Burch. Course: CMPS 1600"));
    	
    			
    }

    @BuilderMethod
    public void RetreatAcceptKingCredits() { //Dylan
    	var node = get(MyNodeLabels.RetreatAcceptKingCredits.toString());
    	node.add(new HideCredits())
    	.add(new ShowMenu());
    }

    @BuilderMethod// 
    public void DeathActions() { //Dylan
    	var node = get(MyNodeLabels.Death.toString());
    	node.add(new HideDialog())
    	.add(new NarrationSequence("You are remembered as being too kind to be king."
    	+ " You die a good man and that is all that ever mattered to you."))
    	.add(new Wait(7))
    	.add(new HideNarration())
    	.add(new FadeOut())
    	.add(new CreditsSequence("Thank you for playing our game!"
    	+ "Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch. Course: CMPS 1600."));
    }

    @BuilderMethod
    public void RetreatDeclineOfferCredits() { //Dylan
    	var node = get(MyNodeLabels.RetreatDeclineOfferCredits.toString());
    	node.add(new HideCredits())
    	.add(new ShowMenu());
    }

    @BuilderMethod//
    public void DenyKingActions() { //Dylan
    	var node = get(MyNodeLabels.DenyKing.toString());
    	node.add(new DialogSequence(doug, king, List.of("I'm sorry, but I can not rule. I am afraid"
    	+ " that the power would change me and force me to harm others"), List.of(" ")))
    	.add(new Wait(6))
    	.add(new HideDialog())
    	.add(new NarrationSequence("Although you are not king you are still a good man"
    	+ " and that is all that has ever mattered to you."))
    	.add(new Wait(4))
    	.add(new HideNarration())
    	.add(new FadeOut())
    	.add(new CreditsSequence("Thank you for playing our game!"
    	+ "Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch"));
    }
    @BuilderMethod //  
    public void KillBanditDeclineOfferCredits() { //Dylan
    	var node = get(MyNodeLabels.EndGame.toString());
    	node.add(new HideCredits())
    	.add(new ShowMenu());
    }
    	

    	

    @BuilderMethod //
    public void  MerchantKillActions() { //Dylan
    var node = get(MyNodeLabels.MerchantKill.toString());
    node.add(new SetPosition(doug, cityExit))
    .add(new WalkTo(doug,merchantbill))
    .add(new Give(doug,bag,merchantbill))
    .add(new DialogSequence(merchantbill, doug, List.of("Thank you for retriving my cargo! But why is there blood on you?"),List.of("Confess", "Lie")));


    }

    @BuilderMethod //
    public void InetruptActions() { //Dylan
    	var node = get(MyNodeLabels.Interupt.toString());
    	node.add(new HideDialog())
    	.add(new DialogSequence(doug, merchantbill, List.of("I KILLED THE BANDIT! I'm sorry I just wanted to get you your bag."), List.of(" ")))
    	.add(new Wait(5))
    	.add(new HideDialog())
    	.add(new SetPosition(merchantbill))
    	.add(new SetPosition(king, city, "Plant"))
    	.add(new DialogSequence(king, doug, List.of("I posed as a merchant to see if I could find an heir"
    	+ " to my throne since I have none. I have been watching you to see if you would be a good fit for king but since you killed the bandit you have failed me Doug Do Good."
    	+ " As king I must punish you for murder. You will be sentenced to life locked in the dungeon."),
    	List.of("Dungeon")));
    }
    @BuilderMethod //
    public void ListenActions() { //Dylan
    	var node = get(MyNodeLabels.Listen.toString());
    	node.add(new DialogSequence(doug, merchantbill, List.of("Oh the blood? I...uh...cut myself while chopping down a tree."), List.of(" ")))
    	.add(new Wait(5))
    	.add(new SetPosition(merchantbill))
    	.add(new SetPosition(king, city, "Plant"))
    	.add(new DialogSequence(king, doug, List.of("Doug Do Good I am the king and I have no heirs to my throne."
    	+ " I posed as a merchant to see who has a kind enough heart to rule the city."
    	+ " I appoint you Doug Do Good as the heir to my throne. Lead well my friend. "
    	+ "A good king can not fold on their morals and I see that you will not"), List.of("Accept Offer", "Decline Offer")));
    }
    @BuilderMethod //  --> roll credits
    public void AcceptOfferActions() { //Dylan
    	var node = get(MyNodeLabels.AcceptOffer.toString());
    	node.add(new HideDialog())
    	.add(new NarrationSequence("The power got to your head and you became a ruthless leader."
    	+ " You were cruel to your people and vicious on the battle field. "
    	+ "After you killed the Bandit your morals were compromised and you were blood thirsty."
    	+ " You died doing what you loved-killing in the battle field."))
    	.add(new Wait(9))
    	.add(new HideNarration())
    	.add(new Die(doug))
    	.add(new HideNarration())
    	.add(new FadeOut())
    	.add(new CreditsSequence("Thank you for playing our game!"
    			+ "Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch. Course: CMPS 1600"));
    }
    @BuilderMethod //  --> Fade Credits
    public void KillBanditAcceptOfferCredits() { //Dylan
    	var node = get(MyNodeLabels.End.toString());
    	node.add(new HideCredits())
    	.add(new ShowMenu());
    }
    @BuilderMethod // 
    public void DungeonActions() { //Dylan
    	var node = get (MyNodeLabels.Dungeon.toString());
    	node.add(new HideDialog())
    	.add(new WalkTo(doug,dungeonDoor))
    	.add(new Exit(doug, dungeonDoor, true)).add(new Enter(doug, insideDungeonDoor, true)).add(new EnableInput())
    	//.add(new SetPosition(doug, dungeon, "DirtPile"))
    	.add(new FadeIn())
    	.add(new NarrationSequence("In prison you think of that day and how you killed that man. "
    	+ "It is all that you think about and it haunts you. "
    	+ "You die in prison knowing that you are a bad man."))
    	.add(new Wait(9))
    	.add(new HideNarration())
    	.add(new Die(doug))
    	.add(new Wait(2))
    	.add(new CreditsSequence("Thank you for playing our game!"
    	+ "Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch. Course: CMPS 1600."));
    }
    @BuilderMethod // 
    public void EndingActions() { //Dylan
    	var node = get(MyNodeLabels.Ending.toString());
    	node.add(new HideCredits())
    	.add(new ShowMenu());
    }
    @BuilderMethod
    public void DeclineQuest() { //Dylan
        var node = get(MyNodeLabels.DeclineQuest.toString());
        node.add(new DialogSequence(doug, merchantbill, List.of("I'm sorry but I can't help you. Goodbye"), List.of(" ")))
        .add(new Wait(3))
        .add(new HideDialog())
        .add(new EnableInput())
        .add(new WalkTo(doug, barrell));
    }


//DYLAN WORK END


    

    @BuilderMethod
    public void Barrell() {
        // 
        var node = get(MyNodeLabels.Barrell.toString());
        node.add(new WalkTo(doug, barrell))
            .add(new HideNarration())
            .add(new EnableInput())
            .add(new Pickup(doug, apple))
            .add(new Face(doug, fountain))
            .add(new DialogSequence(doug, beggar, List.of("At the barrell there is an apple. You pick them up but do not eat them. The lovely fountain captures your eye."), List.of("Fountain")));
    }

    @BuilderMethod
    public void Fountain() { //FIXED FOUNTAIN
        // 
        var node = get(MyNodeLabels.Fountain.toString());
        node.add (new HideDialog()).add(new WalkTo(doug, beggar))
            .add(new DialogSequence(beggar, doug, List.of("I'm so hungry can I have your apples?"), List.of("Give the beggar your apples", "Don't give the beggar your apples")));
    }

    @BuilderMethod
    public void FountainYes() { //FIXED FOUNTAIN
        // 
        var node = get(MyNodeLabels.FountainYes.toString());
        node.add(new HideDialog()).add(new Give(doug, apple, beggar))
            .add(new NarrationSequence("She is so grateful for your kindness that she reccommends you go to the tavern and have some fun."))
            .add(new Wait(4)).add(new HideNarration())
            .add(new Dance(beggar))
            .add(new WalkTo(doug, redhousedoor));


    }
    @BuilderMethod
    public void GoToTavern() { //FIXED FOUNTAIN
    	var node = get(MyNodeLabels.GoToTavern.toString());
    	node.add(new Exit(doug, redhousedoor, true)).add(new Enter(doug, tavernenter, true)).add(new EnableInput());

    }

    @BuilderMethod
    public void TavernActions() { //Joshua Fixed
        // Dylan
        var node = get(MyNodeLabels.TavernActions.toString());
       // node.add(new WalkTo(doug, city, "RedHouseDoor"))
           // .add(new FadeOut())
            //.add(new Enter(doug, taverndoor, true))
            node.add(new FadeIn())//.add(new WalkTo(doug, drunkard))
            .add(new NarrationSequence("At the tavern there are empty chairs to sit in where you can drink. There is also a drunkard who looks like he has had too many drinks.")).add(new Wait(7))
            .add(new HideNarration())
            .add(new DialogSequence(doug, doug, List.of("..."), List.of("Sit", "Talk to Drunkard")));
    }

    @BuilderMethod
    public void TavernSit() {
        // Dylan
        var node = get(MyNodeLabels.TavernSit.toString());
        node.add(new DisableInput()).add(new HideDialog()).add(new Sit(doug, tavernstool)).add(new NarrationSequence("You think to yourself, Why am I sitting instead drinking right now. Lets Party"))
        	.add(new Wait(4)).add(new HideNarration()).add(new Take(doug, bottle,bartender))
            .add(new Drink(doug)).add(new Face(doug, drunkard)).add(new Wait(1))
            .add(new Dance(drunkard))
            .add(new NarrationSequence("You enjoy a beverage, but this guy at the bar is wasted and looks like he needs help. Do you ignore him and party or do you help him?")).add(new Wait(7))
            .add(new HideNarration())
            .add(new EnableInput());
    }

    @BuilderMethod
    public void IgnoreDrunkard() { //Joshua Fixed
        // Dylan
        var node = get(MyNodeLabels.IgnoreDrunkard.toString());
        node.add(new DisableInput()).add(new NarrationSequence("Your thirst is strong so you continue drinking")).add(new Wait(4))
            .add(new HideNarration()).add(new Drink(doug))
            .add(new EnableInput());
    }

    @BuilderMethod
    public void ContinueDrinking() { //Joshua Fixed
        // Dylan
        var node = get(MyNodeLabels.ContinueDrinking.toString());
        node.add(new HideDialog()).add(new DisableInput()).add(new Pocket(doug, bottle)).add(new Take(doug, bottle, bartender)).add(new Drink(doug)).add(new Laugh(doug))
            .add(new Face(doug, fireplace))
            .add(new NarrationSequence("You start feeling tipsy and like a moth to a flame the fire catches your attention. Do you go to the fireplace and watch or do you go to the torch that is on the table?"))
            .add(new Wait(10)).add(new HideNarration()).add(new EnableInput())
            /*.add(new DialogSequence(doug, doug, List.of("..."), List.of("Table", "Fireplace")))*/;
    }

    @BuilderMethod
    public void VisitTavernTable() { //Joshua Fixed
        // Dylan
        var node = get(MyNodeLabels.VisitTavernTable.toString());
        node.add(new HideDialog())
        	//.add(new WalkTo(doug, taverntable))
        	.add(new DisableInput())
            .add(new NarrationSequence("Something feels sketchy about playing with fire. Last fall your Uncle John Do Bad set his house on fire, but he was kinda a loser. Do you pick up the torch?"))
            .add(new Wait(10))
            .add(new HideNarration())
            .add(new EnableInput())
            /*.add(new DialogSequence(doug, doug, List.of("You see a torch flickering enticingly nearby."), List.of("Pick up the torch", "Do not pick up the torch")))*/;
    }

    @BuilderMethod
    public void PickupTorch() {
        // Dylan
        var node = get(MyNodeLabels.PickupTorch.toString());
        node.add(new DisableInput()).add(new Pickup(doug, littorch))
            .add(new NarrationSequence("In an innibriated state you hastily pick up the torch. Ouch you get a splinter and drop the torch. The tavern burns to the ground with you and that weird drunk guy inside."))
            .add(new Wait(7)).add(new HideNarration())//.add(new Putdown(doug,littorch))
            .add(new CreateEffect(doug, com.enums.Effects.Spiralflame))
            .add(new EnableEffect(doug, com.enums.Effects.Spiralflame))
            .add(new Die(doug)).add(new EnableInput())
        // put burning visual effect
        .add(new DialogSequence(doug, doug, List.of("..."), List.of("Burned to death")));
    }

    @BuilderMethod
    public void BurnedToDeath() {
        // Dylan
        var node = get(MyNodeLabels.BurnedToDeath.toString());
        node.add(new HideDialog()).add(new DisableInput()).add(new NarrationSequence("You let drinking and risky behavior take priority over being a good man. You and an innocent drunkard died because of your foolish actions.")).add(new Wait(7))
            .add(new HideNarration())
            .add(new FadeOut()).add(new CreditsSequence("Thank you for playing our game!"
                	+ "Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch"));
    }

    @BuilderMethod
    public void DontPickupTorch() {
        // Dylan
        var node = get(MyNodeLabels.DontPickupTorch.toString());
        node.add(new NarrationSequence("OOHH Doug likes the fireplace. You walk over to the fireplace")).add(new Wait(5))
            .add(new HideNarration())
            .add(new WalkTo(doug, fireplace));
    }

    @BuilderMethod
    public void GoToFireplace() {
        // Dylan
        var node = get(MyNodeLabels.GoToFireplace.toString());
        node.add(new WalkTo(doug, fireplace))
            .add(new LookAt(doug, fireplace))
            .add(new NarrationSequence("While walking over to the fireplace you trip and fall into the fire because you had to much to drink. You burn to death."))
            .add(new Wait(6)).add(new HideNarration()).add(new Kneel(doug)).add(new CreateEffect(doug, com.enums.Effects.Spiralflame))
            .add(new EnableEffect(doug, com.enums.Effects.Spiralflame))//add visual effect
            .add(new Die(doug))
            .add(new FadeOut()).add(new CreditsSequence("Thank you for playing our game!"
                	+ "Contributors: Dylan Mondrus-Jackson Burch-Joshua Burch"));
    }

@BuilderMethod
public void FountainNo() { //FIXED FOUNTAIN
    var node = get(MyNodeLabels.FountainNo.toString());
    node.add(new HideDialog())
    .add(new NarrationSequence("You get thirsty and decide to go to the red house door Tavern and have a drink.")
    .add(new Wait(3)).add(new HideNarration())
    .add(new WalkTo(doug, redhousedoor)));
}
@BuilderMethod
public void ApproachDrunkard() { //Joshua Fixed
    var node = get(MyNodeLabels.ApproachDrunkard.toString());
    node.add(new HideDialog()).add(new DisableInput()).add(new WalkTo(doug,drunkard)).add(new EnableInput()).add(new DialogSequence(doug, drunkard, List.of("Do you need any help? It looks like you are about to pass out."),List.of("Rob Drunkard", "Continue Talking")));
}
@BuilderMethod
public void ContinueTalking() { //Joshua Fixed
	var node = get(MyNodeLabels.ContinueTalking.toString());
	node.add(new DisableInput()).add(new HideDialog()).add(new NarrationSequence("The drunkard is wobbling and slurring his words. The Drunkard is about to passes out.")).add(new Wait(6))
        .add(new HideNarration()).add(new EnableInput())
        .add(new DialogSequence(drunkard, doug, List.of("I...I don't need anythiiiii..."),List.of("Wake Drunkard", "Rob Drunkard")))
         // The drunkard passes out, but revive will be used later to wake him.
        /*.add(new NarrationSequence("The drunkard has passed out. Do you rob the drunkard or wake him?"))
        .add((new HideNarration())).add(new EnableInput())*/;
}

@BuilderMethod
public void RobDrunkard() { //Joshua Fixed
    var node = get(MyNodeLabels.RobDrunkard.toString());
    node.add(new DisableInput()).add(new HideDialog()).add(new Take(doug, bottle, drunkard)).add(new Die(drunkard)).add(new Drink(doug))
        .add(new NarrationSequence("After taking the drunkards drink he passes out and you drink his. You decide to buy another drink.")).add(new Wait(5)).add(new HideNarration()).add(new Face(doug,bartender))
        .add(new WalkTo(doug,bartender))
        .add(new EnableInput());
}
@BuilderMethod//Confuesed
public void WakeDrunkard() { //Joshua Fixed
    var node = get(MyNodeLabels.WakeDrunkard.toString());
    node.add(new DisableInput()).add(new HideDialog()).add(new Face(doug,drunkard)).add(new Die(drunkard)).add(new Wait(1)).add(new NarrationSequence("The drunkard has passed out. Try and Wake him up"))
        .add(new Wait(3)).add((new HideNarration())).add(new Clap(doug)).add(new Wait(1))
        .add(new Revive(drunkard)) //using revive as a way for drunkard to wake up
        .add(new NarrationSequence("Your claps have awoken the drunkard but he is angry that you woke him.")).add(new Wait(4))
        .add(new HideNarration()).add(new EnableInput())
        .add(new DialogSequence(drunkard, doug, List.of("I...I... I'm not drunk. I'm stone cold sober. You are drunk not me!"),List.of("Persist Drunkard", "Ignore Drunkard")))
        /*.add(new NarrationSequence("You are beginning to feel frustrated with the drunkard. Will you persist in trying to help him?"))
        .add(new HideNarration())*/;
}
@BuilderMethod 
//New fixed node Jackson
public void PersistDrunkard() {
var node = get(MyNodeLabels.PersistDrunkard.toString());
node.add(new EnableInput())
.add(new DialogSequence(doug, drunkard, List.of("I will buy you another drink if you promise to leave after."),List.of("Go To Bar")));
}
@BuilderMethod
//Jackson changed node

//IMPORTANT CHANGES MADE
public void GoToBar() {
var node = get(MyNodeLabels.GoToBar.toString());
node.add(new HideDialog())
.add(new NarrationSequence("The drunkard says I've never refused a free drink in my life. You got yourself a deal, Doug Do Good."))
.add(new Wait(5))
.add(new HideNarration())
.add(new WalkTo(doug, bartender))
  //.add(new WalkTo(drunkard, tavernbar))
  .add(new NarrationSequence("The bartender charges you a coin for the drink"))
  .add(new Wait(2))
  .add(new HideNarration())
  .add(new Give(doug, coin, bartender))
  .add(new Give(bartender, bottle, drunkard))
  .add(new Drink(drunkard))
  .add(new DialogSequence(drunkard, doug, List.of("Thank you. I have had a hard time recently and really needed someone to help me and be kind. You are a good man. Can you help me get home?"),List.of("Leave Bar With Drunkard")));
}
@BuilderMethod
//Jackson Updated this node

//IMPORTANT CHANGES MADE
public void TakeDrunkardHome() {
var node = get(MyNodeLabels.TakeDrunkardHome.toString());
node.add(new HideDialog())
.add(new Exit(doug, redhousedoorexit, true))
.add(new Enter(doug, redhousedoor, true))
.add(new FadeIn())
.add(new SetNight())
.add(new Wait(2))
.add(new NarrationSequence("LAST ONE TO MY HOUSE IS A ROTTEN EGG!"))
.add(new Wait(3))
.add(new DisableInput())
.add(new HideNarration())
  .add(new WalkTo(drunkard, drunkardhousedoor))
  .add(new Wait(1))
  .add(new WalkTo(doug, drunkard))
  .add(new Wait(1))
  .add(new NarrationSequence("The drunkard taunts you for beating you in the race, but thanks you for getting him home safely"))
  .add(new Wait(6))
  .add(new HideNarration())
  .add(new Face (drunkard,doug))
  .add(new Dance(drunkard))
  .add(new Wait(3))
  .add(new Wave(drunkard))
  .add(new Exit(drunkard, drunkardhousedoor, true))
  .add(new FadeIn())
  .add(new DialogSequence(doug,doug,List.of("You did a good job getting him home safely"),List.of("Conclusion")));
}
@BuilderMethod
//Jackson Changed

//IMPORTANT CHANGES MADE
public void LeaveBar() {
var node = get(MyNodeLabels.LeaveBar.toString());
node.add(new HideDialog())
.add(new WalkTo(drunkard, redhousedoorexit))
.add(new Wait(1))
.add(new Exit(drunkard, redhousedoorexit, true))
.add(new Enter(drunkard, redhousedoor, true))
.add(new FadeIn())
.add(new NarrationSequence("Follow the drunkard and take him home"))
.add(new Wait(3))
.add(new HideNarration())
.add(new EnableInput());
}
@BuilderMethod
//IMPORTANT CHANGES MADE
public void Conclusion() {
 var node = get(MyNodeLabels.Conclusion.toString());
 node.add(new SetDay()).add(new HideDialog()).add(new SetPosition(drunkard,drunkardhousedoor)).add(new NarrationSequence("The drunkard sobered up the next day. He gratefully approached you and revealed that he was the king's brother. "
         + "He had recently found out that the king was searching for an heir to the throne but deemed him unfit to rule. "
         + "The drunkard took the news harshly, but your kindness saved him from spiraling down a dark path. "
         + "You were a good man and saved him. You win.")).add(new Wait(20))
 .add(new HideNarration()).add(new CreditsSequence("Thank you for playing our game!"
  + "Contributors: Dylan Mondrus Joshua Burch and Jackson Burch"));
}
}