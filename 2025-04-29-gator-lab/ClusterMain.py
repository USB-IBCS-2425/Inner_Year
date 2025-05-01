from graphics import *
from Button import *
import math
import random

def distance(p1, p2):
    return math.sqrt(((p1.getX() - p2.getX()) ** 2) + (p1.getY() - p2.getY()) ** 2)

def getMeanTuple(tuplelist):
    assert len(tuplelist) > 0
    a = []
    b = []
    for i in tuplelist:
        a.append(i[0])
        b.append(i[1])
    return sum(a) / len(a), sum(b) / len(b)

def loadData(win, labelled=True):
    f = open("gatorDataLabeled.txt" if labelled else "gatorDataUnlabeled.txt")
    allData = f.read()
    listData = allData.split()

    gators = []
    crocs = []
    for i in range(0, len(listData), 3 if labelled else 2):
        x = float(listData[i])
        y = float(listData[i+1])
        if labelled:
            if listData[i+2] == "alligator":
                gators.append((x, y))
            else:
                crocs.append((x, y))
        else:
            if random.randint(0, 1) == 1:
                gators.append((x, y))
            else:
                crocs.append((x, y))

    return gators, crocs

def renderData(win, g, c):
    rendered_objects = []

    for i in g:
        p = Point(int(200 + (i)[0]*100), int(500 - i[1]*1.75))
        r = Rectangle(Point(p.getX() - 2, p.getY() - 2), Point(p.getX() + 2, p.getY() + 2))
        r.setFill("red")
        r.draw(win)
        rendered_objects.append(r)

    for i in c:
        p = Point(int(200 + i[0]*100), int(500 - i[1]*1.75))
        r = Rectangle(Point(p.getX() - 2, p.getY() - 2), Point(p.getX() + 2, p.getY() + 2))
        r.setFill("blue")
        r.draw(win)
        rendered_objects.append(r)

    return rendered_objects

def calculateCentroids(win, g, c):
    return getMeanTuple(g), getMeanTuple(c)

def renderCentroids(win, cenG, cenC):
    #p1, p2 = Point(*cenG), Point(*cenC)

    p1 = Point(int(200 + (cenG)[0]*100), int(500 - cenG[1]*1.75))
    p2 = Point(int(200 + (cenC)[0]*100), int(500 - cenC[1]*1.75))

    r1 = Rectangle(Point(p1.getX() - 4, p1.getY() - 4), Point(p1.getX() + 4, p1.getY() + 4))
    r2 = Rectangle(Point(p2.getX() - 4, p2.getY() - 4), Point(p2.getX() + 4, p2.getY() + 4))
    r1.setFill("red")
    r2.setFill("blue")
    r1.draw(win)
    r2.draw(win)

    return [r1, r2] #return rendered objects

def cluster(g, c, cenG, cenC):
    gators = []
    crocs = []
    for i in g + c:
        if distance(Point(*i), Point(*cenG)) < distance(Point(*i), Point(*cenC)):
            gators.append(i)
        else:
            crocs.append(i) #slight bias to crocs, since if theyre equal it goes to them

    return gators, crocs

def main():
    # Create Window
    win = GraphWin("Cluster Example", 800, 800)
    yAx = Line(Point(200, 100), Point(200, 540))
    yAx.draw(win)
    xAx = Line(Point(160, 500), Point(600, 500))
    xAx.draw(win)
    origin = Point(200, 500)
    yLabel = Text(Point(120, 280), "Mass (kg)")
    yLabel.draw(win)
    xLabel = Text(Point(400, 540), "Length (cm)")
    xLabel.draw(win)
    loadButton = Button(win, "Load Data", 80, Point(320, 630))
    quitButton = Button(win, "EXIT", 80, Point(140, 630))
    centButton = Button(win, "Centroid", 80, Point(500, 630))
    clusterButton = Button(win, "Cluster", 80, Point(680, 630))

    outputT = Text(Point(300, 50), "Welcome to the Gator / Crocodile Analyzer")
    outputT.draw(win)

    weightT = Text(Point(120, 720), "WEIGHT")
    weightT.draw(win)
    inputWeight = Entry(Point(240, 720), 20)
    inputWeight.draw(win)
    lengthT = Text(Point(360, 720), "LENGTH")
    lengthT.draw(win)
    inputLength = Entry(Point(480, 720), 20)
    inputLength.draw(win)
    inputButton = Button(win, "Test Data", 80, Point(660, 720))

    #lists of tuples (x, y)
    gators = []
    crocs = []

    #centroids each as a tuple (x, y)
    cenG = 0, 0
    cenC = 0, 0

    #storing the graphical objects
    data_objects = []
    centroid_objects = []


    while True:
        m = win.getMouse()
        if quitButton.isClicked(m):
            win.close()
            break
        
        if loadButton.isClicked(m):
            for i in data_objects:
                i.undraw()
            gators, crocs = loadData(win, False)
            data_objects = renderData(win, gators, crocs)

        if centButton.isClicked(m):
            for i in centroid_objects:
                i.undraw()
            cenG, cenC = calculateCentroids(win, gators, crocs)
            print(cenG)
            print(cenC)
            centroid_objects = renderCentroids(win, cenG, cenC)

        if clusterButton.isClicked(m):
            for i in data_objects:
                i.undraw()
            gators, crocs = cluster(gators, crocs, cenG, cenC)
            data_objects = renderData(win, gators, crocs)

        if inputButton.isClicked(m):
            w = float(inputWeight.getText())
            l = float(inputLength.getText())

            p = Point(l, w)
            d1 = distance(p, Point(*cenG))
            d2 = distance(p, Point(*cenC))
            if d1 < d2:
                outputT.setText("You have an Alligator weighing " + str(w) + " kg and " + str(l) + " meters long")
            else:
                outputT.setText("You have a Crocodile weighing " + str(w) + " kg and " + str(l) + " meters long")

if __name__ == "__main__":
    main()
