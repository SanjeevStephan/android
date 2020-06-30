package com.sanjeev.stephan.murmu.mentorme.quotes

class RobertKiyosaki {
  enum class What_Do_You_Want{
    AUTHOR_NAME,QUOTES,BOOKS_NAME,RELATED_LINKS,INSPIRATIONS
  }
  var array = arrayOf("")


  fun getData(whatDoYouWant: What_Do_You_Want) : Array<String> {


    when (whatDoYouWant) {
      What_Do_You_Want.QUOTES -> array = arrayOf(
              "Money is really just an idea.",
              "Skills make you rich, not theories.",
              "Losers quit when they fail. Winners fail until they succeed.",
              "The love of money is not the root of all evil. The lack of money is the root of all evil.",
              "When you are forced to think, you expand your mental capacity. When you expand your mental capacity, your wealth increases.",
              "When you come to the boundaries of what you know, it is time to make some mistakes.",
              "In the real world, the smartest people are people who make mistakes and learn. In school, the smartest people don’t make mistakes.",
              "Making mistakes isn’t enough to become great. You must also admit the mistake, and then learn how to turn that mistake into an advantage.",
              "You’re only poor if you give up.",
              "Starting a business is like jumping out of an airplane without a parachute. In mid air, the entrepreneur begins building a parachute and hopes it opens before hitting the ground.",
              "You get one life. Live it in a way that it inspires someone",
              "Sometimes you win, sometimes you learn.",
              "The hardest part of change is going through the unknown",
              "The wealthy buy luxuries last, while the poor and middle-class tend to buy luxuries first. Why? Emotional discipline",
              "The problem with having a job is that it gets in the way of getting rich.",
              "Financial struggle is often the direct result of people working all their lives for someone else.",
              "eing an entrepreneur is simply going from one mistake to the next. You must have the fortitude to continue on.",
              "The biggest challenge you have is to challenge your own self doubt and your laziness. It is your self doubt and your laziness that defines and limit who you are",
              "To be a successful business owner and investor, you have to be emotionally neutral to winning and losing. Winning and losing are just part of the game",
              "In today’s rapidly changing world, the people who are not taking risk are the risk takers.",
              "We all have tremendous potential, and we all are blessed with gifts. Yet, the one thing that holds all of us back is some degree of self-doubt. It is not so much the lack of technical information that holds us back, but more the lack of self-confidence.",
              "It’s more important to grow your income than cut your expenses. It’s more important to grow your spirit that cut your dreams.",
              "The most successful people in life are the ones who ask questions. They’re always learning. They’re always growing. They’re always pushing.",
              "Don’t be addicted to money. Work to learn. don’t work for money. Work for knowledge.",
              "Complaining about your current position in life is worthless. Have a spine and do something about it instead.",
              "Getting rich begins with the right mindset, the right words and the right plan",
              "The trouble with school is they give you the answer, then they give you the exam. That’s not life.",
              "The fear of being different prevents most people from seeking new ways to solve their problems",
              "Winners are not afraid of losing. But losers are. Failure is part of the process of success. People who avoid failure also avoid success",
              "Successful people ask questions. They seek new teachers. They’re always learning.",
              "If you want to be rich, you need to develop your vision. You must be standing on the edge of time gazing into the future",
              "If you’re still doing what mommy and daddy said for you to do (go to school, get a job, and save money), you’re losing.",
              "Often, the more money you make the more money you spend; that’s why more money doesn’t make you rich – assets make you rich",
              "The most life destroying word of all is the word tomorrow.",
              "The size of your success is measured by the strength of your desire; the size of your dream; and how you handle disappointment along the way.",
              "I’d rather welcome change than cling to the past",
              "Hoping drains your energy. Action creates energy",
              "Tomorrows only exist in the minds of dreamers and losers",
              "The more a person seeks security, the more that person gives up control over his life.",
              "Everyone can tell you the risk. An entrepreneur can see the reward.",
              "A plan is a bridge to your dreams. Your job is to make the plan or bridge real, so that your dreams will become real. If all you do is stand on the side of the bank and dream of the other side, your dreams will forever be just dreams",
              "You’ll often find that it’s not mom or dad, husband or wife, or the kids that’s stopping you. It’s you. Get out of your own way",
              "The only difference between a rich person and poor person is how they use their time",
              "Your choices decide your fate. Take the time to make the right ones. If you make a mistake, that’s fine; learn from it & don’t make it again",
              "If you’re the kind of person who has no guts, you just give up every time life pushes you. If you’re that kind of person, you’ll live all your life playing it safe, doing the right things, saving yourself for something that never happens. Then, you die a boring old person.",
              "Talk is cheap. Learn to listen with your eyes. Actions do speak louder than words. Watch what a person does more than what he says.",
              "You will make some mistakes but, if you learn from those mistakes, those mistakes will become wisdom and wisdom is essential to becoming wealthy",
              "If you realize that you’re the problem, then you can change yourself, learn something and grow wiser. Don’t blame other people for your problems.",
              "Workers work hard enough to not be fired, and owners pay just enough so that workers won’t quit.",
              "As I said, I wish I could say it was easy. It wasn’t, but it wasn’t hard either. But without a strong reason or purpose, anything in life is hard.",
              "The single most powerful asset we all have is our mind. If it is trained well, it can create enormous wealth in what seems to be an instant",
              "Find the game where you can win, and then commit your life to playing it; and play to win",
              "One of the great things about being willing to try new things and make mistakes is that making mistakes keeps you humble. People who are humble learn more than people who are arrogant.",
              "Intelligence solves problems and produces money. Money without financial intelligence is money soon gone.",
              "Start small and dream big",
              "You’re only poor if you give up. The most important thing is that you did something. Most people only talk and dream of getting rich. You’ve done something.",
              "If you want to be financially-free, you need to become a different person than you are today and let go of whatever has held you back in the past.",
              "The philosophy of the rich and the poor is this: the rich invest their money and spend what is left. The poor spend their money and invest what is left",
              "Sight is what you see with your eyes, vision is what you see with your mind",
              "In school we learn that mistakes are bad, and we are punished for making them. Yet, if you look at the way humans are designed to learn, we learn by making mistakes. We learn to walk by falling down. If we never fell down, we would never walk.",
              "Never say you cannot afford something. That is a poor man’s attitude. Ask HOW to afford it.",
              "F.O.C.U.S – Follow One Course Until Successful",
              "It is easy to stay the same but it is not easy to change. Most people choose to stay the same all their lives",
              "Your future is created by what you do today, not tomorrow",
              "Excuses cost a dime and that’s why the poor could afford a lot of it.",
              "I find so many people struggling, often working harder, simply because they cling to old ideas. They want things to be the way they were; they resist change. Old ideas are their biggest liability. It is a liability simply because they fail to realize that while that idea or way of doing something was an asset yesterday, yesterday is gone.",
              "One of the most stupid things to do is to pretend you are smart. When you pretend to be smart, you are at the height of stupidity.",
              "Find out where you are at, where you are going and build a plan to get there.",
              "I am concerned that too many people are focused too much on money and not on their greatest wealth, which is their education. If people are prepared to be flexible, keep an open mind and learn, they will grow richer and richer through the changes. If they think money will solve the problems, I am afraid those people will have a rough ride",
              "People who dream small dreams continue to live as small people",
              "The richest people in the world build networks; everyone else is trained to look for work",
              "There are those who make things happen, there are those who watch things happen and there are those who say ‘what happened?",
              "People without financial knowledge, who take advice from financial experts are like lemmings simply following their leader. They race for the cliff and leap into the ocean of financial uncertainty, hoping to swim to the other side",
              "Too many people are too lazy to think. Instead of learning something new, they think the same thought day in day out.",
              "Education is cheap; experience is expensive.",
              "People need to wake up and realize that life doesn’t wait for you. If you want something, get up and go after it",
              "If you want to be rich, simply serve more people.",
              "Face your fears and doubts, and new worlds will open to you",
              "A winning strategy must include losing.",
              "If you want to go somewhere, it is best to find someone who has already been there",
              "You’re only poor if you give up. The most important thing is that you did something. Most people only talk and dream of getting rich. You’ve done something",
              "Success is a poor teacher. We learn the most about ourselves when we fail, so don’t be afraid of failing. Failing is part of the process of success. You cannot have success without failure",
              "",
              "",
              "",
              "",
              "",
              "",
              "",
              "",
              ""



      )
      What_Do_You_Want.INSPIRATIONS -> array = arrayOf(
              "How to Create confidence"
      )


    }

    return array
  }
}