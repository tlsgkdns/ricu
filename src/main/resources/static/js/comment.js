async function getComment(bno)
{
    const result = await axios.get(`/comments/list/${bno}`)
    return result
}

async function getList({bno, page, size, goLast})
{
    const result = await axios.get(`/comments/list/${bno}`, {params: {page, size}})

    if(goLast)
    {
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno, page:lastPage, size:size})
    }
    return result.data
}

async function addComment(commentObj)
{
    const response = await axios.post(`/comments/`, commentObj)
    return response.data
}

async function getComment(commentID)
{
    const result = await axios.get(`/comments/${commentID}`)
}

async function modifyComment(commentObj)
{
    const result = await axios.put(`/comments/${commentObj.commentID}, commentObj`)
}

async function removeComment(commentID)
{
    const result = await axios.delete(`/comments/${commentID}`)
    return result.data
}

async function getCommentCount(bno)
{
    const result = await axios.get(`/comments/count/${bno}`)
    return result.data
}