# Oblig3_IN2010

gjort endringer på linje `84`, `126` og `130`

~~~ java
  // endret fra b.liste.add til h.liste[i].liste.add. b er en kopi og vil ikke endre selve listen
  else{
      h.liste[i].liste.add(tall);
  }
~~~

~~~ java
  // endret fra b = null til h.liste[i] = null. b er en kopi og vil ikke endre selve listen
  if(b.liste.size() == 1){
      h.liste[i] = null;
  }
  // endret fra b.liste.remove til h.liste[i].liste.remove. b er en kopi og vil ikke endre selve listen
  else{
      h.liste[i].liste.remove(tall);
  }
~~~
