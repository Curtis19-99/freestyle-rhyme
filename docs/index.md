# Freestyle Rhyme

## Project introduction and description
 
The purpose of this app is to create a rhyme generator for when you are freestyling, and you get stuck, or run out of rhymes and need ideas. This app will rhyme single words or last words of sentences to keep in the format of freestyling. It will also have a random word generator to help stimulate ideas and subject matter to rap about. This app can be used for different purposes as well for example it could be used as just a simple rhyme generator or for writing poems. The user will have the ability to enter in a word and it will bring up a list of words that rhyme with the entered word. The user will be able to hit a random word button, and it will bring up a random word to rhyme with.

My motivations for selecting this particular topic for my app were simple, to make something that my friends can use and enjoy with me. The main reason I chose to develop this app was because my friends all liked the idea and we all agreed that we could see ourselves using the app when we freestyle. This is a potentially useful app because it can be used whenever a rhyme is needed. This gives the app more versatility than just for freestyling as it could be used to help write poetry or just anytime you need to find a word that rhymes with a certain word.

Some key functionalities of my app are the random rhyme generator and the random word button. The rhyme generator is simple, a word is put into a search bar at the top and it outputs a list of words that rhyme with the entered word. The random word button just brings up a random word in the Api. This random word idea is implemented to help the flow of ideas when you are freestyling.

## [Intended users and user stories](user-stories.md)

## Current state of the app

* Description

The current state of the app is it is running and the main functionality is completed. This main functionality consists of bringing a list of rhymes for entered words. It also includes, a random word button that is essential for ideas in freestyling. Some incomplete elements are no icon on the floating action button. Also, there was nothing added to include voice to text. I have not found any major bugs yet. 

* Improvements

A better first screen. Right now it is just the Google sign in, but I believe a lot could be done to make it look better.

Have custom logos for the app icon as well as the random word button. 

Incorporate some different colors at the top.

Incorporate a logo onto the floating action button.

* Stretch Goals

I would like to add a feature where you can search a word to rhyme with and from the list of word that come from the entered word you could select a word and it would bring up rhymes instantly for that word.

Add a function where you can select a word and bring up the definition of the word.

## [Wireframe diagram](wireframe.md)

## [ERD diagram](erd.md)

## [Data Definition Language (DDL)](ddl.md)

## [Copyrights and licenses](copyrightAndLicenses.md)

## Build Instructions 

* Clone repository using SSH key in the code pull down menu from https://github.com/Curtis19-99/freestyle-rhyme.

* Import the project into IntelliJ using SHH key (git@github.com:Curtis19-99/freestyle-rhyme.git).

* Build project and run application.

## Technical requirements and dependencies

* Minimum Android API required is Android 5.0 Lolipop API 21.

* Hardware used

BLU G5 for external device to run app on.

* Third party libraries
> Google sign in
>
> Gson
>
> Retrofit
>
>ReactiveX

* Words Api, https://www.wordsapi.com/
> This service will be used to bring all the rhyme data. The app will not function without access to this Api.

* Google sign in, https://developers.google.com/identity/sign-in/android/
> Google sign in will be used to access stored data through each account. This will give the user the ability to remember recent words used. The app will work without access to Google sign in. However, it will not have the ability to remember recent words used.

* Safe permission 

> permission to use internet.

## [Basic user instructions](userInstructions.md)
