package at.cgsit.train.java.patterns.sealed;

public sealed class Rectangle extends Shape
    permits FilledRectangle, BorderRectangle { }


