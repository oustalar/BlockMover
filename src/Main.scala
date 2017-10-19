import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Color, Dimension, Graphics, Rectangle}
import javax.swing.{JButton, JFrame, JPanel}

import Game.{setDefaultCloseOperation, setSize, setTitle, setVisible}

import scala.collection.mutable

/**
  * Created by oustalar on 10/17/17.
  */

object MainPanel extends JPanel with ActionListener{

  import java.util.concurrent.ThreadLocalRandom

  var listOfRectangles: mutable.MutableList[Rectangle] = scala.collection.mutable.MutableList()

  var numberOfBlocks = 0
  var i = 0

  val generatorButton = new JButton("Generate Block")
  add(generatorButton)
  generatorButton.addActionListener(this)

  override def paintComponent(g: Graphics): Unit = {
    def randomRGB = ThreadLocalRandom.current.nextInt(0, 255+1)
    super.paintComponent(g)
      for (rect <- listOfRectangles) {
        println("helloweld")
        g.setColor(new Color(randomRGB,randomRGB,randomRGB))
        g.drawRect(rect.x,rect.y,rect.width,rect.height)
      }


  }

  override def actionPerformed(actionEvent: ActionEvent): Unit = {
    if(actionEvent.getSource == generatorButton){
      def randomX: Int = ThreadLocalRandom.current.nextInt(0, Game.getPreferredSize.width)
      listOfRectangles ++= (0 to 1000).map(_ => new Rectangle(randomX,randomX,randomX,randomX))
    }
    repaint()
  }
}

object Game extends JFrame {
  setTitle("Move Block")
  setPreferredSize(new Dimension(600,700))
  setResizable(true)
  setVisible(true)
  add(MainPanel)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  this.pack()
}

object MainGame {
  def main(args: Array[String]): Unit = {
    Game
  }
}