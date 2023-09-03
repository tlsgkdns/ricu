async function addLike(bno)
{
    console.log("ADDING LIKE")
    const result = await axios.get(`/boardRest/like/${bno}`)
    console.log(result.data)
    return result.data
}

async function cancelLike(bno)
{
    console.log("Cancel LIKE..")
    const result = await axios.get(`/boardRest/cancelLike/${bno}`)
    return result.data
}

async function getBoard(bno)
{
    const result = await axios.get(`/boardRest/${bno}`)
    return result.data
}

async function getBoardList(page, writer, galleryID, size)
{
    const result = await axios.get(`/boardRest/galleryID/${galleryID}/${writer}`, {params: {page, size}})
    return result.data
}