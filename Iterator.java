interface Iterator {
 public void first(); // 取り出し位置を最初の要素へ変える
 public void next(); // 取り出し位置を次の要素へ変える
 public boolean isDone(); // 取り出し位置が最後を超えたか？
 public Object getItem(); // 現在の取り出し位置から取り出す
}

class GameListIterator implements Iterator {
 private GameListAggregate aggregate;
 private int current;
 public GameListIterator(GameListAggregate aggregate) {
 this.aggregate = aggregate;
 }

 @Override
 public void first() {
 current = 0;
 }
 @Override
 public void next() {
 current += 1;
 }
 @Override
 public boolean isDone() {
 if (current >= aggregate.getNumberOfStock()) {
 return true;
 }
 else {
 return false;
 }
 }

 @Override
 public Object getItem() {
 return aggregate.getAt(current);
 }
}
interface Aggregate {
 public Iterator createIterator();
}

class GameListAggregate implements Aggregate {
 private Game[] list = new Game[20];
 private int numberOfStock;
 @Override
 public Iterator createIterator() {
 return new GameListIterator(this);
 }
 public void add(Game game) {
 list[numberOfStock] = game;
 numberOfStock += 1;
 }
 public Object getAt(int number) {
 return list[number];
 }
 public int getNumberOfStock() {
 return numberOfStock;
 }

public class IteratorSample1 {
 public static void main(String[] args) {
 GameListAggregate gameListAggregate = new GameListAggregate();
 Iterator iterator = gameListAggregate.createIterator();
 gameListAggregate.add(new Game("スターダストクルセイダース", 4678));
 gameListAggregate.add(new Game("ダイアモンドは砕けない", 9744));
 gameListAggregate.add(new Game("黄金の風", 6255));
 gameListAggregate.add(new Game("ストーンオーシャン", 8712));
 iterator.first(); // まず探す場所を先頭位置にしてもらう
 while ( ! iterator.isDone() ) { // まだある？ まだあるよ！
 Game game = (Game)iterator.getItem(); // あらよっと (と受取る)
 System.out.println(game.getName());
 iterator.next(); // 次を頂戴
 }
 }
}