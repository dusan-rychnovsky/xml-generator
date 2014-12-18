/*
* Copyright (c) 2014 by Casenet, LLC
*
* This file is protected by Federal Copyright Law, with all rights
* reserved. No part of this file may be reproduced, stored in a
* retrieval system, translated, transcribed, or transmitted, in any
* form, or by any means manual, electric, electronic, mechanical,
* electro-magnetic, chemical, optical, or otherwise, without prior
* explicit written permission from Casenet, LLC.
*/
package cz.dusanrychnovsky.xmlgenerator.schema.graph;

public class SchemaGraphBuilder {

    public void clear() {

    }

    public SchemaGraph getResult() {
        return new SchemaGraph(new RootNode(null));
    }
}
